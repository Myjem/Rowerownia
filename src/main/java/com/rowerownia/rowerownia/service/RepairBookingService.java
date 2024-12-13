package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.DTO.RepairBookingRequest;
import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.entity.Repair;
import com.rowerownia.rowerownia.entity.RepairBooking;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.RepairBookingRepository;
import com.rowerownia.rowerownia.repository.RepairRepository;
import com.rowerownia.rowerownia.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class RepairBookingService {
    private final RepairBookingRepository repairBookingRepository;
    private final UserRepository userRepository;
    private final RepairRepository repairRepository;

    @Autowired
    public RepairBookingService(RepairBookingRepository repairBookingRepository, UserRepository userRepository, RepairRepository repairRepository) {
        this.repairBookingRepository = repairBookingRepository;
        this.userRepository = userRepository;
        this.repairRepository = repairRepository;
    }

    public List<RepairBooking> getRepairBookings() {
        return repairBookingRepository.findAll();
    }

    public List<RepairBooking> getRepairBookingsByUserId(Integer userId) {
        return repairBookingRepository.findByUser_UserId(userId);
    }

    public List<RepairBooking> getPendingRepairBookings(Integer userId) {
        return repairBookingRepository.findByUser_UserIdAndRepairStatus(userId, Enums.status.PENDING);
    }

    @Transactional
    public void addNewRepairBooking(RepairBookingRequest repairBookingRequest) {
        User user = userRepository.findById(repairBookingRequest.getUserId())
                .orElseThrow(() -> new IllegalStateException("User with id " + repairBookingRequest.getUserId() + " does not exists"));

        Repair repair = repairRepository.findById(repairBookingRequest.getRepairId())
                .orElseThrow(() -> new IllegalStateException("Repair with id " + repairBookingRequest.getRepairId() + " does not exists"));

        if(repair.getRepairName().equals("deleted_repair")){
            throw new IllegalStateException("You can't use this service");
        }

        RepairBooking repairBooking = new RepairBooking(
                user,
                LocalDate.parse(repairBookingRequest.getRbookingDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(repairBookingRequest.getRepairDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                Enums.status.PENDING,
                repair
        );

        if(repairBooking.getRbookingDate().isAfter(repairBooking.getRepairDate())) {
            throw new IllegalStateException("Invalid booking dates");
        }
        List<RepairBooking> dateBookings= repairBookingRepository.findByRepairDate(repairBooking.getRepairDate());
        if(!dateBookings.isEmpty()){
            int time=0;
            for(RepairBooking booking: dateBookings){
                time+=booking.getRepair().getRepairTime();
            }
            if(time+repair.getRepairTime()>8){
                throw new IllegalStateException("Too many repairs on this day");
            }
        }
        repairBookingRepository.save(repairBooking);
    }

    @Transactional
    public void finishBooking(Integer repairBookingId) {
        RepairBooking repairBooking =repairBookingRepository.findById(repairBookingId)
                .orElseThrow(() -> new IllegalStateException("Repair booking with id " + repairBookingId + " does not exists"));
        repairBooking.setRepairStatus(Enums.status.FINISHED);
    }
    @Transactional
    public void cancelBooking(Integer repairBookingId) {
        RepairBooking repairBooking =repairBookingRepository.findById(repairBookingId)
                .orElseThrow(() -> new IllegalStateException("Repair booking with id " + repairBookingId + " does not exists"));
        repairBooking.setRepairStatus(Enums.status.DELETED);
    }





}
