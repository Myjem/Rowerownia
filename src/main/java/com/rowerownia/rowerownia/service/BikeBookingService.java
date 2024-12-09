package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.entity.BikeBooking;
import com.rowerownia.rowerownia.repository.BikeBookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeBookingService {

    private final BikeBookingRepository bikeBookingRepository;

    public BikeBookingService(BikeBookingRepository bikeBookingRepository) {
        this.bikeBookingRepository = bikeBookingRepository;
    }

    public List<BikeBooking> getBikeBooking() {
        return List.of(

        );
    }
}
