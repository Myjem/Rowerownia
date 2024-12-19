package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.entity.Repair;
import com.rowerownia.rowerownia.entity.RepairBooking;
import com.rowerownia.rowerownia.repository.RepairBookingRepository;
import com.rowerownia.rowerownia.repository.RepairRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {
    private final RepairRepository repairRepository;
    private final RepairBookingRepository repairBookingRepository;

    @Autowired
    public RepairService(RepairRepository repairRepository, RepairBookingRepository repairBookingRepository) {
        this.repairRepository = repairRepository;
        this.repairBookingRepository = repairBookingRepository;
    }

    public void addNewRepair(Repair repair) {
        repairRepository.save(repair);
    }

    public void deleteRepair(Integer repairId) {
        boolean exists = repairRepository.existsById(repairId);
        if(!exists){
            throw new IllegalStateException("repair with id " + repairId + " does not exists");
        }

        Repair repair = repairRepository.findById(repairId).orElseThrow(() -> new IllegalStateException("repair with id " + repairId + " does not exists"));
        String repairName = repair.getRepairName();
        if(repairName.equals("deleted_Repair")){
            throw new IllegalStateException("You can't delete this repair");
        }

        List<RepairBooking> repairBookings = repairBookingRepository.findByRepair_RepairId(repairId);
        if(!repairBookings.isEmpty()){
            for (RepairBooking repairBooking : repairBookings) {
                if(repairBooking.getRepairStatus().equals(Enums.status.PENDING)){
                    repairBooking.setRepairStatus(Enums.status.DELETED);
                }
                repairBooking.setRepair(repairRepository.findByRepairName("deleted_Repair"));
                repairBookingRepository.save(repairBooking);
            }
        }
        repairRepository.deleteById(repairId);
    }

    @Transactional
    public void updateRepair(Integer repairId, String repairName, Double repairTime, Integer repairPrice) {
        Repair repair = repairRepository.findById(repairId).orElseThrow(() -> new IllegalStateException("repair with id " + repairId + " does not exists"));

        if (repairName != null &&
                !repairName.isEmpty() &&
                !repair.getRepairName().equals(repairName)){
            repair.setRepairName(repairName);
        }

        if (repairTime != null &&
                repairTime > 0 &&
                !repair.getRepairTime().equals(repairTime)){
            repair.setRepairTime(repairTime);
        }

        if (repairPrice != null &&
                repairPrice > 0 &&
                !repair.getRepairPrice().equals(repairPrice)){
            repair.setRepairPrice(repairPrice);
        }
    }

    public List<Repair> getRepairs() {
        return repairRepository.findAll();
    }

}
