package com.rowerownia.rowerownia.controller;

import com.rowerownia.rowerownia.DTO.BikeBookingRequest;
import com.rowerownia.rowerownia.entity.BikeBooking;
import com.rowerownia.rowerownia.service.BikeBookingService;
import jakarta.validation.Valid;
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

    @PutMapping(path = "/auth/worker/bikeBooking/cancel/{bikeBookingId}")
    public void cancelBooking(
            @PathVariable("bikeBookingId") Integer bikeBookingId) {
        bikeBookingService.cancelBooking(bikeBookingId);
    }

    @PutMapping(path = "/auth/worker/bikeBooking/{bikeBookingId}/finish")
    public void finishBooking(
            @PathVariable("bikeBookingId") Integer bikeBookingId,
            @RequestBody(required = false) List<Integer> brokenBikeIds) {
        bikeBookingService.finishBooking(bikeBookingId, brokenBikeIds);
    }

    @GetMapping(path = "/auth/worker/bikeBooking/pending")
    public List<BikeBooking> getPendingBikeBookings() {
        return bikeBookingService.getPendingBikeBookings();
    }

    @GetMapping(path = "/auth/user/bikeBooking")
    public List<BikeBooking> getUserBikeBookings() {
        return bikeBookingService.getUserBikeBookings();
    }

    @PutMapping(path = "/auth/user/bikeBooking/cancel/{bikeBookingId}")
    public void cancelUserBooking(
            @PathVariable("bikeBookingId") Integer bikeBookingId) {
        bikeBookingService.cancelUserBooking(bikeBookingId);
    }

    @PostMapping(path = "/auth/user/bikeBooking/add")
    public void addNewUserBikeBooking(@Valid @RequestBody BikeBookingRequest bikeBookingRequest) {
        bikeBookingService.addNewUserBikeBooking(bikeBookingRequest);
    }


//    @GetMapping(path = "/auth/user/me/bikeBooking/count")
//    public Integer getBikeBookingCountByUserId() {
//        return bikeBookingService.getUserBikeBookingCount();
//    }
//
//    @GetMapping(path = "/auth/worker/user/{userId}/bikeBooking")
//    public List<BikeBooking> getBikeBookingsByUserId(@PathVariable("userId") Integer userId) {
//        return bikeBookingService.getBikeBookingsByUserId(userId);
//    }

}
