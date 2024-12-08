package com.rowerownia.rowerownia.configuration;

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
            User defu = new User(
                    "login",
                    "pass",
                    LocalDate.of(2000, 1, 1),
                    "name",
                    "surname"
            );
            User work = new User(
                    "login2",
                    "passssss",
                    LocalDate.of(2010, 1, 1),
                    "nameeee",
                    "surnameeeee"
            );

            repository.saveAll(
                    List.of(defu, work)
            );
        };
    }
}
