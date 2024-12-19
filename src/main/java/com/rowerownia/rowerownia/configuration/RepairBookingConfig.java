package com.rowerownia.rowerownia.configuration;

import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.entity.Repair;
import com.rowerownia.rowerownia.entity.RepairBooking;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.RepairBookingRepository;
import com.rowerownia.rowerownia.repository.RepairRepository;
import com.rowerownia.rowerownia.repository.UserRepository;
import com.rowerownia.rowerownia.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class RepairBookingConfig {
    @Bean
    CommandLineRunner repairBookingCommandLineRunner(RepairBookingRepository repository, UserService userService, UserRepository userRepository, RepairRepository repairRepository) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return args -> {
            User user = new User(
                    "check",
                    passwordEncoder.encode("pass_check"),
                    LocalDate.of(1990,2,13),
                    "Czarek",
                    "Czekowy"
            );
            userRepository.save(user);
            Repair r1 = new Repair(
                    "full service",
                    2.5,
                    250
            );
            repairRepository.save(r1);

            RepairBooking repairBooking = new RepairBooking(
                    user,
                    LocalDate.of(2021, 5, 1),
                    LocalDate.of(2021, 5, 3),
                    Enums.status.PENDING,
                    r1
            );
            repository.save(repairBooking);

        };
    }
}
