package com.rowerownia.rowerownia.configuration;

import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;


@Configuration
public class UserConfig  {
    @Bean
    CommandLineRunner usercommandLineRunner(UserRepository repository) {
        return args -> {
            User person = new User(
                    "logg",
                    "pass",
                    LocalDate.of(2000, 1, 2),
                    "jan",
                    "kowalski"
            );
            User worker = new User(
                    "worklog",
                    "pass",
                    LocalDate.of(2010, 1, 1),
                    "kuba",
                    "nowak",
                    Enums.level.WORKER
            );

            repository.saveAll(
                    List.of(person, worker)
            );
        };
    }
}
