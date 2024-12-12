package com.rowerownia.rowerownia.controller;

import com.rowerownia.rowerownia.DTO.RepairBookingRequest;
import com.rowerownia.rowerownia.entity.RepairBooking;
import com.rowerownia.rowerownia.service.RepairBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/repairBooking")

public class RepairBookingController {
    private final RepairBookingService repairBookingService;

    @Autowired
    public RepairBookingController(RepairBookingService repairBookingService) {
    this.repairBookingService = repairBookingService;
    }

    @GetMapping
    public List<RepairBooking> getRepairBookings() {
    return repairBookingService.getRepairBookings();
    }

    @PostMapping
    public void addNewRepairBooking(@RequestBody RepairBookingRequest repairBookingRequest) {
        repairBookingService.addNewRepairBooking(repairBookingRequest);
    }
    @PutMapping(path = "{repairBookingId}/finish")
    public void finishBooking(
            @PathVariable("repairBookingId") Integer repairBookingId){
        repairBookingService.finishBooking(repairBookingId);
    }
    @PutMapping(path = "{repairBookingId}/cancel")
    public void cancelBooking(
            @PathVariable("repairBookingId") Integer repairBookingId) {
        repairBookingService.cancelBooking(repairBookingId);
    }

}
