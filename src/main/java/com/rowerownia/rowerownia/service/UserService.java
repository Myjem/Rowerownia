package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.DTO.BikeBookingRequest;
import com.rowerownia.rowerownia.DTO.UserDto;
import com.rowerownia.rowerownia.dtomapper.UserDtoMapper;
import com.rowerownia.rowerownia.entity.BikeBooking;
import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.entity.RepairBooking;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.BikeBookingRepository;
import com.rowerownia.rowerownia.repository.RepairBookingRepository;
import com.rowerownia.rowerownia.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final BikeBookingRepository bikeBookingRepository;
    private final RepairBookingRepository repairBookingRepository;
    public static final int MAX_FAILED_ATTEMPTS = 5;

    @Autowired
    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper, BikeBookingRepository bikeBookingRepository, RepairBookingRepository repairBookingRepository) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.bikeBookingRepository = bikeBookingRepository;
        this.repairBookingRepository = repairBookingRepository;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Transactional
    public void deleteUser(Integer userId){
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("user with id " + userId + " does not exists");
        }
        List<BikeBooking> bikeBookings = bikeBookingRepository.findByUser_UserId(userId);
        if(!bikeBookings.isEmpty()){
            for (BikeBooking bikeBooking : bikeBookings) {
                bikeBookingRepository.delete(bikeBooking);
            }
        }
        List<RepairBooking> repairBookings = repairBookingRepository.findByUser_UserId(userId);
        if(!repairBookings.isEmpty()){
            for (RepairBooking repairBooking : repairBookings) {
                repairBookingRepository.delete(repairBooking);
            }
        }
        userRepository.deleteById(userId);
    }

    public void updateLoggedUser(String name, String surname,LocalDate dataBirth){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByLogin(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
        if (name != null &&
                !name.isEmpty() &&
                !Objects.equals(user.getName(),name)){
            user.setName(name);
        }
        if (surname != null &&
                !surname.isEmpty() &&
                !Objects.equals(user.getSurname(),surname)){
            user.setSurname(surname);
        }
        if(!dataBirth.toString().isEmpty() &&
                !Objects.equals(user.getBirthDate(),dataBirth)){
            user.setBirthDate(dataBirth);
        }
        userRepository.save(user);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().
                stream().
                map(userDtoMapper).collect(Collectors.toList());
    }

    public UserDto getLoggedUser(){
        System.out.println("testtttttttttt");
        Authentication authentication = getAuthentication();
        System.out.println(authentication.getPrincipal());
//        if (authentication == null) {
//            throw new IllegalStateException("User not logged in");
//        }
        String username = ((org.springframework.security.core.userdetails.User) getAuthentication().getPrincipal()).getUsername();
        System.out.println(username);
        User currentUser = userRepository.findUserByLogin(username).orElseThrow(() -> new IllegalStateException("User not found"));
        System.out.println("------------");
        System.out.println(currentUser);
        return new UserDto(
                currentUser.getUserId(),
                currentUser.getLogin(),
                currentUser.getPassword(),
                currentUser.getBirthDate().toString(),
                currentUser.getName(),
                currentUser.getSurname(),
                currentUser.getAccessLevel().toString(),
                currentUser.getFailedLoginAttempts(),
                currentUser.getIsBlocked());
    }

    @Transactional
    public void deleteLoggedUser(){
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("User not logged in");
        }
        String username = ((org.springframework.security.core.userdetails.User) getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findUserByLogin(username).orElseThrow(() -> new IllegalStateException("User not found"));
        List<BikeBooking> bikeBookings = bikeBookingRepository.findByUser_UserId(user.getUserId());
        if(!bikeBookings.isEmpty()){
            for (BikeBooking bikeBooking : bikeBookings) {
                bikeBookingRepository.delete(bikeBooking);
            }
        }
        List<RepairBooking> repairBookings = repairBookingRepository.findByUser_UserId(user.getUserId());
        if(!repairBookings.isEmpty()){
            for (RepairBooking repairBooking : repairBookings) {
                repairBookingRepository.delete(repairBooking);
            }
        }
        userRepository.deleteById(user.getUserId());
    }

    public void unlockUser(Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exists"));
        user.setIsBlocked(false);
        userRepository.save(user);
    }

    public void accessLevel(Integer userId, String accessLevel){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exists"));
        if (accessLevel.equals("WORKER") || accessLevel.equals("USER")){
            user.setAccessLevel(Enums.level.valueOf(accessLevel));
        }
        userRepository.save(user);
    }

    public void plusFailedAttempts(User user){
        int newAttempts = user.getFailedLoginAttempts() + 1;
        user.setFailedLoginAttempts(newAttempts);
        if(newAttempts >= MAX_FAILED_ATTEMPTS){
            user.setIsBlocked(true);
        }
        userRepository.save(user);
    }

    public void resetFailedAttempts(User user){
        user.setFailedLoginAttempts(0);
        userRepository.save(user);
    }






//    public  void addNewUser(User user) {
//        Optional<User> userByLogin = userRepository.findUserByLogin(user.getLogin());
//        if(userByLogin.isPresent()){
//            throw new IllegalStateException("login taken");
//        }
//        userRepository.save(user);
//    }
//
//    @Transactional
//    public void updateUser(Integer userId, String name, String surname){
//        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exists"));
//
//        if (name != null &&
//                name.length() > 0 &&
//                !Objects.equals(user.getName(),name)){
//            user.setName(name);
//        }
//        if (surname != null &&
//                surname.length() > 0 &&
//                !Objects.equals(user.getSurname(),surname)){
//            user.setSurname(surname);
//        }
//    }
//
//    public Integer getFailedAttempts(Integer userId){
//        User user = userRepository.findUserByUserId(userId);
//        if (user == null) {
//            throw new IllegalStateException("User with id " + userId + " does not exist");
//        }
//        return user.getFailedLoginAttempts();
//    }
//
//    public boolean isBlocked(Integer userId){
//        User user = userRepository.findUserByUserId(userId);
//        if (user == null) {
//            throw new IllegalStateException("User with id " + userId + " does not exist");
//        }
//        return user.getIsBlocked();
//    }

}
