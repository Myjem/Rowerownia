package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.entity.Repair;
import com.rowerownia.rowerownia.repository.RepairRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {
    private final RepairRepository repairRepository;

    @Autowired
    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public void addNewRepair(Repair repair) {
        repairRepository.save(repair);
    }

    public List<Repair> getRepairs() {
        return repairRepository.findAll();
    }

    public void deleteRepair(Integer repairId) {
        boolean exists = repairRepository.existsById(repairId);
        if(!exists){
            throw new IllegalStateException("repair with id " + repairId + " does not exists");
        }
        repairRepository.deleteById(repairId);
    }

    @Transactional
    public void updateRepair(Integer repairId, String repairName, Double repairTime, Integer repairPrice) {
        Repair repair = repairRepository.findById(repairId).orElseThrow(() -> new IllegalStateException("repair with id " + repairId + " does not exists"));

        if (repairName != null &&
                repairName.length() > 0 &&
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
}
