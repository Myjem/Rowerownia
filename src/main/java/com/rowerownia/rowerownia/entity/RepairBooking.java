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
    @Column(name = "repairBookingId", updatable = false)
    private Integer repairBookingId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @Column(name = "rbookingDate", nullable = false)
    private LocalDate rbookingDate;
    @Column(name = "repairDate", nullable = false)
    private LocalDate repairDate;
    @Column(name = "repairStatus", nullable = false)
    private Enums.status repairStatus;
    @ManyToOne
    @JoinColumn(name = "repairId")
    private Repair repair;

    public RepairBooking() {
    }

    public RepairBooking(User user, LocalDate rbookingDate, LocalDate repairDate, Enums.status repairStatus, Repair repair) {
        this.user = user;
        this.rbookingDate = rbookingDate;
        this.repairDate = repairDate;
        this.repairStatus = repairStatus;
        this.repair = repair;
    }

    public Integer getRepairBookingId() {
        return repairBookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    @Override
    public String toString() {
        return "repairBooking{" +
                "repairBookingId=" + repairBookingId +
                ", user=" + user +
                ", rbookingDate=" + rbookingDate +
                ", repairDate=" + repairDate +
                ", repairStatus=" + repairStatus +
                ", repair=" + repair +
                '}';
    }
}