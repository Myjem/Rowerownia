package com.rowerownia.rowerownia.service;


import com.rowerownia.rowerownia.entity.Bike;
import com.rowerownia.rowerownia.entity.BikeBooking;
import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.repository.BikeBookingRepository;
import com.rowerownia.rowerownia.repository.BikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    private final BikeRepository bikeRepository;
    private final BikeBookingRepository bikeBookingRepository;

    @Autowired
    public BikeService(BikeRepository bikeRepository, BikeBookingRepository bikeBookingRepository) {
        this.bikeRepository = bikeRepository;
        this.bikeBookingRepository = bikeBookingRepository;
    }

    public void addNewBike(Bike bike) {
        bikeRepository.save(bike);
    }


    public List<Bike> getBikes() {
        return bikeRepository.findAll();
    }

    @Transactional
    public void deleteBike(Integer bikeId) {
        boolean exists = bikeRepository.existsById(bikeId);
        if(!exists){
            throw new IllegalStateException("bike with id " + bikeId + " does not exists");
        }

        Bike bike = bikeRepository.findById(bikeId).orElseThrow(() -> new IllegalStateException("bike with id " + bikeId + " does not exists"));

        String bikeName = bike.getBikeName();
        if(bikeName.equals("deleted_Bike")){
            throw new IllegalStateException("You can't delete this bike");
        }

        List<BikeBooking> bikeBookings = bikeBookingRepository.findByBikes_BikeId(bikeId);
        if(!bikeBookings.isEmpty()){
            for (BikeBooking bikeBooking : bikeBookings) {
                if(bikeBooking.getBikeStatus().equals(Enums.status.PENDING)){
                    bikeBooking.setBikeStatus(Enums.status.DELETED);
                }
                bikeBooking.getBikes().remove(bikeRepository.findById(bikeId).orElseThrow(() -> new IllegalStateException("bike does not exists")));
                bikeBooking.getBikes().add(bikeRepository.findBikeByBikeName("deleted_Bike"));
                bikeBookingRepository.save(bikeBooking);
            }
        }
        bikeRepository.deleteById(bikeId);
    }
    @Transactional
    public void updateBike(Integer bikeId, String bikeName, String bikeSize, Integer bikePrice) {
        Bike bike = bikeRepository.findById(bikeId).orElseThrow(() -> new IllegalStateException("bike with id " + bikeId + " does not exists"));

        if (bikeName != null &&
                !bikeName.isEmpty() &&
                !bike.getBikeName().equals(bikeName)){
            bike.setBikeName(bikeName);
        }

        if (bikeSize != null &&
                !bikeSize.isEmpty() &&
                !bike.getBikeSize().equals(bikeSize)){
            bike.setBikeSize(bikeSize);
        }

        if (bikePrice != null &&
                bikePrice > 0 &&
                !bike.getBikePrice().equals(bikePrice)){
            bike.setBikePrice(bikePrice);
        }
    }
    @Transactional
    public void setStatus(Integer bikeId,boolean broken) {
        Bike bike = bikeRepository.findById(bikeId).orElseThrow(() -> new IllegalStateException("bike with id " + bikeId + " does not exists"));
        bike.setBroken(broken);
        List<BikeBooking> bikeBookings = bikeBookingRepository.findByBikes_BikeId(bikeId);
        if(!bikeBookings.isEmpty()){
            for (BikeBooking bikeBooking : bikeBookings) {
                bikeBooking.setBikeStatus(Enums.status.DELETED);
            }
        }

    }




}
