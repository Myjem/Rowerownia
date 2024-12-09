package com.rowerownia.rowerownia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table
public class Repair {
    @Id
    @SequenceGenerator(
            name = "repair_sequence",
            sequenceName = "repair_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "repair_sequence")
    private Integer repairId;
    private String repairName;
    private Double repairTime;
    private Integer repairPrice;

    public Repair() {
    }

    public Repair(String repairName, Double repairTime, Integer repairPrice) {
        this.repairName = repairName;
        this.repairTime = repairTime;
        this.repairPrice = repairPrice;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public Double getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Double repairTime) {
        this.repairTime = repairTime;
    }

    public Integer getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(Integer repairPrice) {
        this.repairPrice = repairPrice;
    }

    public Integer getRepairId() {
        return repairId;
    }

    @Override
    public String toString() {
        return "repair{" +
                "repairId=" + repairId +
                ", repairName='" + repairName + '\'' +
                ", repairTime=" + repairTime +
                ", repairPrice=" + repairPrice +
                '}';
    }
}
