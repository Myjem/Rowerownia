package com.rowerownia.rowerownia;

import com.rowerownia.rowerownia.entity.Bike;
import com.rowerownia.rowerownia.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RowerowniaApplication implements CommandLineRunner {
    @Autowired
    BikeRepository bikeRepository;

    public static void main(String[] args) {
        SpringApplication.run(RowerowniaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Bike bike1 = new Bike("Rower", "M",10, false);
        bikeRepository.save(bike1);
    }
}
