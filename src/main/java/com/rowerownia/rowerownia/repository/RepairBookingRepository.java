package com.rowerownia.rowerownia.repository;

import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.entity.RepairBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepairBookingRepository
        extends JpaRepository<RepairBooking, Integer> {
    List<RepairBooking> findByRepair_RepairId(Integer repairId);
    List<RepairBooking> findByUser_UserId(Integer userId);
    List<RepairBooking> findByRepairDate(LocalDate repairDate);
    List<RepairBooking> findByUser_UserIdAndRepairStatus(Integer userId, Enums.status repairStatus);
}

