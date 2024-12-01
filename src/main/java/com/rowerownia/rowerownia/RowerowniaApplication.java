package com.rowerownia.rowerownia;

import com.rowerownia.rowerownia.entity.Bike;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class RowerowniaApplication  {
    public static void main(String[] args) {
        SpringApplication.run(RowerowniaApplication.class, args);
    }



}
