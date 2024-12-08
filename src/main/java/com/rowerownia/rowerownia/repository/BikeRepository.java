package com.rowerownia.rowerownia.repository;

import com.rowerownia.rowerownia.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BikeRepository
        extends JpaRepository<Bike,Integer> {

}
