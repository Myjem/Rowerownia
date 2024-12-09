package com.rowerownia.rowerownia.repository;

import com.rowerownia.rowerownia.entity.BikeBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeBookingRepository extends JpaRepository<BikeBooking,Integer> {
}
