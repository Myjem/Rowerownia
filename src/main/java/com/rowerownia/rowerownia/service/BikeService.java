package com.rowerownia.rowerownia.service;


import com.rowerownia.rowerownia.entity.Bike;
import com.rowerownia.rowerownia.repository.BikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    private final BikeRepository bikeRepository;

    @Autowired
    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public void addNewBike(Bike bike) {
        bikeRepository.save(bike);
    }


    public List<Bike> getBikes() {
        return bikeRepository.findAll();
    }

    public void deleteBike(Integer bikeId) {
        boolean exists = bikeRepository.existsById(bikeId);
        if(!exists){
            throw new IllegalStateException("bike with id " + bikeId + " does not exists");
        }
        bikeRepository.deleteById(bikeId);
    }
    @Transactional
    public void updateBike(Integer bikeId, String bikeName, String bikeSize, Integer bikePrice) {
        Bike bike = bikeRepository.findById(bikeId).orElseThrow(() -> new IllegalStateException("bike with id " + bikeId + " does not exists"));

        if (bikeName != null &&
                bikeName.length() > 0 &&
                !bike.getBikeName().equals(bikeName)){
            bike.setBikeName(bikeName);
        }

        if (bikeSize != null &&
                bikeSize.length() > 0 &&
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
    }


}
