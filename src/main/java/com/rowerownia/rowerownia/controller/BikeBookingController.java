package com.rowerownia.rowerownia.controller;

import com.rowerownia.rowerownia.DTO.BikeBookingRequest;
import com.rowerownia.rowerownia.entity.BikeBooking;
import com.rowerownia.rowerownia.service.BikeBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")

public class BikeBookingController {
    private final BikeBookingService bikeBookingService;

    @Autowired
    public BikeBookingController(BikeBookingService bikeBookingService) {
        this.bikeBookingService = bikeBookingService;
    }

    @GetMapping(path = "/auth/worker/bikeBooking")
    public List<BikeBooking> getBikeBookings() {
        return bikeBookingService.getBikeBookings();
    }

    @GetMapping(path = "/auth/user/{userId}/bikeBooking")
    public List<BikeBooking> getBikeBookingsByUserId(@PathVariable("userId") Integer userId) {
        return bikeBookingService.getBikeBookingsByUserId(userId);
    }

    @GetMapping(path = "/auth/user/{userId}/bikeBooking/pending")
    public List<BikeBooking> getPendingBikeBookings(@PathVariable("userId") Integer userId) {
        return bikeBookingService.getPendingBikeBookings(userId);
    }

    @GetMapping(path = "/auth/user/{userId}/bikeBooking/count")
    public Integer getBikeBookingCountByUserId(@PathVariable("userId") Integer userId) {
        return bikeBookingService.getBikeBookingCountByUserId(userId);
    }

    @PostMapping(path = "/auth/bikeBooking/add/{userId}")
    public void addNewBikeBooking(@RequestBody BikeBookingRequest bikeBookingRequest) {
        bikeBookingService.addNewBikeBooking(bikeBookingRequest);
    }

    @PutMapping(path = "/auth/worker/bikeBooking/finish/{bikeBookingId}")
    public void finishBooking(
            @PathVariable("bikeBookingId") Integer bikeBookingId,
            @RequestParam(required = false) List<Integer> brokenBikeIds) {
        bikeBookingService.finishBooking(bikeBookingId, brokenBikeIds);
    }

    @PutMapping(path = "/auth/user/bikeBooking/cancel/{bikeBookingId}")
    public void cancelBooking(
            @PathVariable("bikeBookingId") Integer bikeBookingId) {
        bikeBookingService.cancelBooking(bikeBookingId);
    }

}
