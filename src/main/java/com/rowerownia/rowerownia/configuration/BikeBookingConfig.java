package com.rowerownia.rowerownia.configuration;

import com.rowerownia.rowerownia.entity.Bike;
import com.rowerownia.rowerownia.entity.BikeBooking;
import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.BikeBookingRepository;
import com.rowerownia.rowerownia.repository.BikeRepository;
import com.rowerownia.rowerownia.repository.UserRepository;
import com.rowerownia.rowerownia.service.BikeService;
import com.rowerownia.rowerownia.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BikeBookingConfig {
    @Bean
    CommandLineRunner bikeBookingCommandLineRunner(BikeBookingRepository repository, UserService userService, UserRepository userRepository, BikeRepository bikeRepository) {
        return args -> {
            User user = userRepository.findUserByUserId(1);
            List<Bike> bikes = new ArrayList<Bike>();
            bikes.add(bikeRepository.findBikeByBikeId(2));
            BikeBooking bikeBooking = new BikeBooking(
                    user,
                    LocalDate.of(2021, 5, 1),
                    LocalDate.of(2021, 5, 3),
                    LocalDate.of(2021, 5, 5),
                    Enums.status.PENDING,
                    bikes
            );
            repository.save(bikeBooking);
        };
    }

}
