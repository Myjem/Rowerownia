package com.rowerownia.rowerownia.controller;


import com.rowerownia.rowerownia.entity.Bike;
import com.rowerownia.rowerownia.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bike")
public class BikeController {
    private final BikeService bikeService;

    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public List<Bike> getBikes() {
        return bikeService.getBikes();
    }

    @PostMapping
    public void addNewBike(@RequestBody Bike bike) {
        bikeService.addNewBike(bike);
    }

    @DeleteMapping(path="{bikeId}")
    public void deleteUser(@PathVariable("bikeId") Integer bikeId) {
        bikeService.deleteBike(bikeId);
    }

    @PutMapping(path = "{bikeId}")
    public void updateBike(
            @PathVariable("bikeId") Integer bikeId,
            @RequestParam(required = false) String bikeName,
            @RequestParam(required = false) String bikeSize,
            @RequestParam(required = false) Integer bikePrice) {
        bikeService.updateBike(bikeId, bikeName, bikeSize, bikePrice);
    }

    @PutMapping(path = "{bikeId}/status")
    public void setStatus(
            @PathVariable("bikeId") Integer bikeId,
            @RequestParam(required = false) boolean broken) {
        bikeService.setStatus(bikeId, broken);
    }
}
