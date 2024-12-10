package com.rowerownia.rowerownia.controller;

import com.rowerownia.rowerownia.entity.BikeBooking;
import com.rowerownia.rowerownia.service.BikeBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bikeBooking")

public class BikeBookingController {
    private final BikeBookingService bikeBookingService;

    @Autowired
    public BikeBookingController(BikeBookingService bikeBookingService) {
        this.bikeBookingService = bikeBookingService;
    }

    @GetMapping
    public List<BikeBooking> getBikeBookings() {
        return bikeBookingService.getBikeBookings();
    }

    @PostMapping
    public void addNewBikeBooking(@RequestBody BikeBooking bikeBooking) {
        bikeBookingService.addNewBikeBooking(bikeBooking);
    }

    @PutMapping(path = "{bikeBookingId}/finish")
    public void finishBooking(
            @PathVariable("bikeBookingId") Integer bikeBookingId,
            @RequestParam(required = false) List<Integer> brokenBikeIds) {
        bikeBookingService.finishBooking(bikeBookingId, brokenBikeIds);
    }

    @PutMapping(path = "{bikeBookingId}/cancel")
    public void cancelBooking(
            @PathVariable("bikeBookingId") Integer bikeBookingId) {
        bikeBookingService.cancelBooking(bikeBookingId);
    }

}
