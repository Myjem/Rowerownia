package com.rowerownia.rowerownia.controller;


import com.rowerownia.rowerownia.entity.Repair;
import com.rowerownia.rowerownia.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/repair")
public class RepairController {
    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping
    public List<Repair> getRepairs() {
        return repairService.getRepairs();
    }

    @PostMapping
    public void addNewRepair(@RequestBody Repair repair) {
        repairService.addNewRepair(repair);
    }

    @DeleteMapping(path="{repairId}")
    public void deleteRepair(@PathVariable("repairId") Integer repairId) {
        repairService.deleteRepair(repairId);
    }

    @PutMapping(path = "{repairId}")
    public void updateRepair(
            @PathVariable("repairId") Integer repairId,
            @RequestParam(required = false) String repairName,
            @RequestParam(required = false) Double repairTime,
            @RequestParam(required = false) Integer repairPrice) {
        repairService.updateRepair(repairId, repairName, repairTime, repairPrice);
    }
}
