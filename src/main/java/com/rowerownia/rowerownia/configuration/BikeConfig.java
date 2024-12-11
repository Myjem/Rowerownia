package com.rowerownia.rowerownia.configuration;

import com.rowerownia.rowerownia.repository.BikeRepository;
import com.rowerownia.rowerownia.entity.Bike;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BikeConfig {
    @Bean
    CommandLineRunner bikecommandLineRunner(BikeRepository repository) {
        return args -> {
            Bike lost = new Bike(
                    "deleted Bike",
                    "none",
                    0,
                    false
            );
            Bike road = new Bike(
                    "Kross",
                    "L",
                    150,
                    false
            );

            repository.saveAll(
                    List.of(lost, road)
            );
        };
    }
}
