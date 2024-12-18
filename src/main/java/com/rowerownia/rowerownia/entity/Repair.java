package com.rowerownia.rowerownia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "repair")
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
    @Column(name= "repairId" ,updatable = false)
    private Integer repairId;
    @Column(name = "repairName", nullable = false)
    private String repairName;
    @Column(name = "repairTime", nullable = false,length = 2,precision = 2)
    private Double repairTime;
    @Column(name = "repairPrice", nullable = false,length = 4)
    @PositiveOrZero(message = "Price must be positive")
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
