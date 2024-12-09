package com.rowerownia.rowerownia.configuration;

import com.rowerownia.rowerownia.entity.Repair;
import com.rowerownia.rowerownia.repository.RepairRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class RepairConfig {
    @Bean
    CommandLineRunner repaircommandLineRunner(RepairRepository repository) {
        return args -> {
            Repair repair1 = new Repair(
                   "cleaning",
                    0.5,
                    50
            );
            Repair repair2 = new Repair(
                    "tire replacement",
                    0.25,
                    30
            );

            repository.saveAll(
                    List.of(repair1, repair2)
            );
        };
    }
}
