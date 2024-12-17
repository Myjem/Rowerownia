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

    public List<UserDto> getUsers() {
        return userRepository.findAll().
                stream().
                map(userDtoMapper).collect(Collectors.toList());
    }

    public  void addNewUser(User user) {
        Optional<User> userByLogin = userRepository.findUserByLogin(user.getLogin());
        if(userByLogin.isPresent()){
            throw new IllegalStateException("login taken");
        }
        userRepository.save(user);
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

    @Transactional
    public void updateUser(Integer userId, String name, String surname){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exists"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(user.getName(),name)){
            user.setName(name);
        }
        if (surname != null &&
                surname.length() > 0 &&
                !Objects.equals(user.getSurname(),surname)){
            user.setSurname(surname);
        }
    }

    public void plusFailedAttempts(User user){
        int newAttepts = user.getFailedLoginAttempts() + 1;
        user.setFailedLoginAttempts(newAttepts);
        if(newAttepts>=MAX_FAILED_ATTEMPTS){
            user.setIsBlocked(true);
        }
        userRepository.save(user);
    }

    public void resetFailedAttempts(User user){
        user.setFailedLoginAttempts(0);
        userRepository.save(user);
    }

    public void unlockUser(Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exists"));
        user.setIsBlocked(false);
        userRepository.save(user);
    }

    public Integer getFailedAttempts(Integer userId){
        return userRepository.findUserByUserId(userId).getFailedLoginAttempts();
    }

    public boolean isBlocked(Integer userId){
        return userRepository.findUserByUserId(userId).getIsBlocked();
    }

    public void accessLevel(Integer userId, String accessLevel){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exists"));
        if (accessLevel == "WORKER" && accessLevel == "USER"){
            user.setAccessLevel(Enums.level.valueOf(accessLevel));
        }
        userRepository.save(user);
    }


}
