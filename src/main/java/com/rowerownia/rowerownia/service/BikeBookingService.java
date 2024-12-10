package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.entity.Bike;
import com.rowerownia.rowerownia.entity.BikeBooking;
import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.repository.BikeBookingRepository;
import com.rowerownia.rowerownia.repository.BikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeBookingService {

    private final BikeBookingRepository bikeBookingRepository;
    private final BikeService bikeService;

    @Autowired
    public BikeBookingService(BikeBookingRepository bikeBookingRepository, BikeService bikeService) {
        this.bikeBookingRepository = bikeBookingRepository;
        this.bikeService = bikeService;
    }

    public List<BikeBooking> getBikeBookings() {
        return bikeBookingRepository.findAll();
    }

    @Transactional
    public void addNewBikeBooking(BikeBooking bikeBooking) {
        if(bikeBooking.getBbookingDate().isAfter(bikeBooking.getBstartDate()) || bikeBooking.getBstartDate().isAfter(bikeBooking.getBendDate()) || bikeBooking.getBbookingDate().isAfter(bikeBooking.getBendDate())) {
            throw new IllegalStateException("Invalid booking dates");
        }
        List<BikeBooking> confict = bikeBookingRepository.findByBikesInAndBstartDateLessThanEqualAndBendDateGreaterThanEqual(bikeBooking.getBikes(), bikeBooking.getBstartDate(), bikeBooking.getBendDate());
        if (!confict.isEmpty()) {
            throw new IllegalStateException("Bike is already booked");
        }
        List<Bike> brokenBikes = bikeBooking.getBikes().stream()
                .filter(bike -> bike.isBroken())
                .collect(Collectors.toList());

        if (!brokenBikes.isEmpty()) {
            throw new IllegalStateException("Not all bikes are available");
        }
        bikeBookingRepository.save(bikeBooking);
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

    public void cancelBooking(Integer bikeBookingId) {
        BikeBooking bikeBooking = bikeBookingRepository.findById(bikeBookingId).orElseThrow(() -> new IllegalStateException("Booking with id " + bikeBookingId + " does not exists"));
        bikeBooking.setBikeStatus(Enums.status.DELETED);
    }

}













