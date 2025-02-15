package com.rowerownia.rowerownia.repository;

import com.rowerownia.rowerownia.entity.Bike;
import com.rowerownia.rowerownia.entity.BikeBooking;
import com.rowerownia.rowerownia.entity.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BikeBookingRepository
        extends JpaRepository<BikeBooking,Integer> {
    List<BikeBooking> findByBikesInAndBstartDateLessThanEqualAndBendDateGreaterThanEqual(List<Bike> bikes, LocalDate bstartDate, LocalDate bendDate); //funkcja zapobiegajaca overbookingu
    List<BikeBooking> findByBikes_BikeId(Integer bikeId);
    List<BikeBooking> findByUser_UserId(Integer userUserId);
    List<BikeBooking> findByBikes_BikeIdAndBikeStatus(Integer bikeId, Enums.status bikeStatus);
    List<BikeBooking> findByUser_UserIdAndBikeStatus(Integer userUserId, Enums.status bikeStatus);

    List<BikeBooking> findByBikeStatus(Enums.status bikeStatus);
}
