package com.rowerownia.rowerownia.controller;

import com.rowerownia.rowerownia.DTO.RepairBookingRequest;
import com.rowerownia.rowerownia.entity.RepairBooking;
import com.rowerownia.rowerownia.service.RepairBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")

public class RepairBookingController {
    private final RepairBookingService repairBookingService;

    @Autowired
    public RepairBookingController(RepairBookingService repairBookingService) {
    this.repairBookingService = repairBookingService;
    }

    @GetMapping(path = "/auth/worker/repairBooking")
    public List<RepairBooking> getRepairBookings() {
    return repairBookingService.getRepairBookings();
    }

    @GetMapping(path = "/auth/user/{userId}/repairBooking")
    public List<RepairBooking> getRepairBookingsByUserId(@PathVariable("userId") Integer userId) {
    return repairBookingService.getRepairBookingsByUserId(userId);
    }

    @GetMapping(path = "/auth/user/{userId}/repairBooking/pending")
    public List<RepairBooking> getPendingRepairBookings(@PathVariable("userId") Integer userId) {
    return repairBookingService.getPendingRepairBookings(userId);
    }


    @PostMapping(path = "/auth/user/repairBooking/add/{userId}")
    public void addNewRepairBooking(@RequestBody RepairBookingRequest repairBookingRequest) {
        repairBookingService.addNewRepairBooking(repairBookingRequest);
    }
    @PutMapping(path = "/auth/worker/repairBooking/finish/{repairBookingId}")
    public void finishBooking(
            @PathVariable("repairBookingId") Integer repairBookingId){
        repairBookingService.finishBooking(repairBookingId);
    }
    @PutMapping(path = "/auth/user/repairBooking/cancel/{repairBookingId}")
    public void cancelBooking(
            @PathVariable("repairBookingId") Integer repairBookingId) {
        repairBookingService.cancelBooking(repairBookingId);
    }

}
