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

    @PutMapping(path = "/auth/worker/repairBooking/{repairBookingId}/cancel")
    public void cancelRepairBooking(
            @PathVariable("repairBookingId") Integer repairBookingId) {
        repairBookingService.cancelRepairBooking(repairBookingId);
    }

    @PutMapping(path = "/auth/worker/repairBooking/{repairBookingId}/finish")
    public void finishRepairBooking(
            @PathVariable("repairBookingId") Integer repairBookingId){
        repairBookingService.finishRepairBooking(repairBookingId);
    }

    @GetMapping(path = "/auth/user/repairBooking")
    public List<RepairBooking> getUserRepairBookings() {
        return repairBookingService.getUserRepairBookings();
    }

    @PutMapping(path = "/auth/user/repairBooking/cancel/{repairBookingId}")
    public void cancelUserRepairBooking(
            @PathVariable("repairBookingId") Integer repairBookingId) {
        repairBookingService.cancelUserRepairBooking(repairBookingId);
    }

    @PostMapping(path = "/auth/user/repairBooking/add")
    public void addNewUserRepairBooking(@RequestBody RepairBookingRequest repairBookingRequest) {
        repairBookingService.addNewUserRepairBooking(repairBookingRequest);
    }




//
//    @GetMapping(path = "/auth/user/me/repairBooking/pending")
//    public List<RepairBooking> getPendingUserRepairBookings() {
//    return repairBookingService.getPendingUserRepairBookings();
//    }
//
//    @GetMapping(path = "/auth/worker/user/{userId}/repairBooking")
//    public List<RepairBooking> getRepairBookingsByUserId(@PathVariable("userId") Integer userId) {
//        return repairBookingService.getRepairBookingsByUserId(userId);
//    }
//
//    @GetMapping(path = "/auth/worker/repairBooking/{repairBookingId}")
//    public RepairBooking getRepairBookingByrepairBookingId(@PathVariable("repairBookingId") Integer repairBookingId) {
//        return repairBookingService.getRepairBookingByrepairBookingId(repairBookingId);
//    }

}
