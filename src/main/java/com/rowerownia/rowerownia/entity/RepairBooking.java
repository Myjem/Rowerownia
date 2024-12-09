package com.rowerownia.rowerownia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "repairBooking")
public class RepairBooking {
    @Id
    @SequenceGenerator(
            name = "repairBooking_sequence",
            sequenceName = "repairBooking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "repairBooking_sequence")
    private Integer repairBookingId;
    private Integer userId;
    private LocalDate rbookingDate;
    private LocalDate repairDate;
    private Enums.status repairStatus;

    public RepairBooking() {
    }

    public RepairBooking(Integer userId, LocalDate rbookingDate, LocalDate repairDate, Enums.status repairStatus) {
        this.userId = userId;
        this.rbookingDate = rbookingDate;
        this.repairDate = repairDate;
        this.repairStatus = repairStatus;
    }

    public Integer getRepairBookingId() {
        return repairBookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getRbookingDate() {
        return rbookingDate;
    }

    public void setRbookingDate(LocalDate rbookingDate) {
        this.rbookingDate = rbookingDate;
    }

    public LocalDate getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDate repairDate) {
        this.repairDate = repairDate;
    }

    public Enums.status getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(Enums.status repairStatus) {
        this.repairStatus = repairStatus;
    }

    @Override
    public String toString() {
        return "repairBooking{" +
                "repairBookingId=" + repairBookingId +
                ", userId=" + userId +
                ", rbookingDate=" + rbookingDate +
                ", repairDate=" + repairDate +
                ", repairStatus=" + repairStatus +
                '}';
    }
}