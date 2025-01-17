package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.DTO.BikeBookingRequest;
import com.rowerownia.rowerownia.entity.Bike;
import com.rowerownia.rowerownia.entity.BikeBooking;
import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.BikeBookingRepository;
import com.rowerownia.rowerownia.repository.BikeRepository;
import com.rowerownia.rowerownia.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeBookingService {

    private final BikeBookingRepository bikeBookingRepository;
    private final BikeService bikeService;
    private final UserRepository userRepository;
    private final BikeRepository bikeRepository;
    private final UserService userService;

    @Autowired
    public BikeBookingService(BikeBookingRepository bikeBookingRepository, BikeService bikeService, UserRepository userRepository, BikeRepository bikeRepository, UserService userService) {
        this.bikeBookingRepository = bikeBookingRepository;
        this.bikeService = bikeService;
        this.userRepository = userRepository;
        this.bikeRepository = bikeRepository;
        this.userService = userService;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public List<BikeBooking> getBikeBookings() {
        return bikeBookingRepository.findAll();
    }

    @Transactional
    public void cancelBooking(Integer bikeBookingId) {
        BikeBooking bikeBooking = bikeBookingRepository.findById(bikeBookingId).orElseThrow(() -> new IllegalStateException("Booking with id " + bikeBookingId + " does not exists"));
        bikeBooking.setBikeStatus(Enums.status.DELETED);
    }

    @Transactional
    public void finishBooking(Integer bikeBookingId, List<Integer> brokenBikeIds) {
        BikeBooking bikeBooking = bikeBookingRepository.findById(bikeBookingId).orElseThrow(() -> new IllegalStateException("Booking with id " + bikeBookingId + " does not exists"));
        if (brokenBikeIds != null) {
            for (Integer bikeId : brokenBikeIds) {
                bikeService.setStatus(bikeId, true);
            }
        }
        bikeBooking.setBikeStatus(Enums.status.FINISHED);
    }

    public List<BikeBooking> getPendingBikeBookings() {
        return bikeBookingRepository.findByBikeStatus(Enums.status.PENDING);
    }

    public List<BikeBooking> getUserBikeBookings() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("User not logged in");
        }
        String username = ((org.springframework.security.core.userdetails.User) getAuthentication().getPrincipal()).getUsername();
        return bikeBookingRepository.findByUser_UserId(userRepository.findUserByLogin(username).get().getUserId());
    }
@Transactional
    public void cancelUserBooking(Integer bikeBookingId) {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("User not logged in");
        }
        String username = ((org.springframework.security.core.userdetails.User) getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findUserByLogin(username).orElseThrow(() -> new IllegalStateException("User not found"));
        if(!bikeBookingRepository.findById(bikeBookingId).get().getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalStateException("You can't cancel this booking");
        }
        BikeBooking bikeBooking = bikeBookingRepository.findById(bikeBookingId).orElseThrow(() -> new IllegalStateException("Booking with id " + bikeBookingId + " does not exists"));
        bikeBooking.setBikeStatus(Enums.status.DELETED);
    }

    @Transactional
    public void addNewUserBikeBooking(BikeBookingRequest bikeBookingRequest) {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("User not logged in");
        }
        String username = ((org.springframework.security.core.userdetails.User) getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findUserByLogin(username)
                .orElseThrow(() -> new IllegalStateException("User does not exists"));

        if(!user.getUserId().equals(bikeBookingRequest.getUserId())){
            throw new IllegalStateException("You can't make booking for other user");
        }

        List<Bike> bikes = bikeBookingRequest.getBikeIds().stream()
                .map(bikeId -> bikeRepository.findById(bikeId).orElseThrow(() -> new IllegalStateException("Bike with id " + bikeId + " does not exists")))
                .collect(Collectors.toList());

        for (Bike bike : bikes) {
            if (bike.getBikeName().equals("deleted_Bike")) {
                throw new IllegalStateException("You can't use this bike");
            }
        }

        BikeBooking bikeBooking = new BikeBooking(
                user,
                LocalDate.parse(bikeBookingRequest.getBookingDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(bikeBookingRequest.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(bikeBookingRequest.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                Enums.status.PENDING,
                bikes
        );

        if(bikeBooking.getBbookingDate().isAfter(bikeBooking.getBstartDate()) || bikeBooking.getBstartDate().isAfter(bikeBooking.getBendDate()) || bikeBooking.getBbookingDate().isAfter(bikeBooking.getBendDate())) {
            throw new IllegalStateException("Invalid booking dates");
        }
        for (Bike bike : bikes) {
            List<BikeBooking> conflicts = bikeBookingRepository.findByBikes_BikeId(bike.getBikeId());

            for (BikeBooking existingBooking : conflicts) {
                if (existingBooking.getBikeStatus() == Enums.status.DELETED) {
                    continue;
                }

                boolean isOverlapping = !(bikeBooking.getBendDate().isBefore(existingBooking.getBstartDate()) ||
                        bikeBooking.getBstartDate().isAfter(existingBooking.getBendDate()));

                if (isOverlapping) {
                    throw new IllegalStateException("Bike " + bike.getBikeName() + " is already booked from " +
                            existingBooking.getBstartDate() + " to " + existingBooking.getBendDate());
                }
            }
        }

        List<Bike> brokenBikes = bikeBooking.getBikes().stream()
                .filter(bike -> bike.isBroken())
                .collect(Collectors.toList());

        if (!brokenBikes.isEmpty()) {
            throw new IllegalStateException("Not all bikes are available");
        }
        bikeBookingRepository.save(bikeBooking);
    }


//    public List<BikeBooking> getBikeBookingsByUserId(Integer userId) {
//        return bikeBookingRepository.findByUser_UserId(userId);
//    }
//
//    public Integer getUserBikeBookingCount() {
//        return bikeBookingRepository.findByUser_UserId(userService.getLoggedUser().userId()).size();
//    }
//
//    @Transactional
//    public void addNewBikeBooking(BikeBookingRequest bikeBookingRequest) {
//        User user = userRepository.findById(bikeBookingRequest.getUserId())
//                .orElseThrow(() -> new IllegalStateException("User with id " + bikeBookingRequest.getUserId() + " does not exists"));
//
//        List<Bike> bikes = bikeBookingRequest.getBikeIds().stream()
//                .map(bikeId -> bikeRepository.findById(bikeId).orElseThrow(() -> new IllegalStateException("Bike with id " + bikeId + " does not exists")))
//                .collect(Collectors.toList());
//
//        for (Bike bike : bikes) {
//            if (bike.getBikeName().equals("deleted_bike")) {
//                throw new IllegalStateException("You can't use this bike");
//            }
//        }
//
//        BikeBooking bikeBooking = new BikeBooking(
//                user,
//                LocalDate.parse(bikeBookingRequest.getBookingDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                LocalDate.parse(bikeBookingRequest.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                LocalDate.parse(bikeBookingRequest.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                Enums.status.PENDING,
//                bikes
//        );
//
//        if(bikeBooking.getBbookingDate().isAfter(bikeBooking.getBstartDate()) || bikeBooking.getBstartDate().isAfter(bikeBooking.getBendDate()) || bikeBooking.getBbookingDate().isAfter(bikeBooking.getBendDate())) {
//            throw new IllegalStateException("Invalid booking dates");
//        }
//        List<BikeBooking> confict = bikeBookingRepository.findByBikesInAndBstartDateLessThanEqualAndBendDateGreaterThanEqual(bikeBooking.getBikes(), bikeBooking.getBstartDate(), bikeBooking.getBendDate());
//        if (!confict.isEmpty()) {
//            throw new IllegalStateException("Bike is already booked");
//        }
//        List<Bike> brokenBikes = bikeBooking.getBikes().stream()
//                .filter(bike -> bike.isBroken())
//                .collect(Collectors.toList());
//
//        if (!brokenBikes.isEmpty()) {
//            throw new IllegalStateException("Not all bikes are available");
//        }
//        bikeBookingRepository.save(bikeBooking);
//    }

}













