package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.entity.bikeBooking;
import com.rowerownia.rowerownia.entity.enums;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Service
public class bikeBookingService {
    @GetMapping
    public List<bikeBooking> getBikeBooking() {
        return List.of(
                new bikeBooking(3,
                        LocalDate.of(2024,9,12),
                        LocalDate.of(2024,9,12),
                        LocalDate.of(2024,9,12),
                        enums.status.PENDING
                )
        );
    }
}
