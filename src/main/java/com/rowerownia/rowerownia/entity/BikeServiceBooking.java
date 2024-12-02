package com.rowerownia.rowerownia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bikeserviceBooking")
public class BikeServiceBooking {
    @Id
    @SequenceGenerator(
            name = "bikeserviceBooking_sequence",
            sequenceName = "bikeserviceBooking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bikeserviceBooking_sequence")
    private Integer bikeserviceBookingId;
    private Integer userId;
    private LocalDate sbookingDate;
    private LocalDate serviceDate;
    private enums.status serviceStatus;

    public BikeServiceBooking() {
    }

    public BikeServiceBooking(Integer userId, LocalDate sbookingDate, LocalDate serviceDate, enums.status serviceStatus) {
        this.userId = userId;
        this.sbookingDate = sbookingDate;
        this.serviceDate = serviceDate;
        this.serviceStatus = serviceStatus;
    }

    public Integer getBikeserviceBookingId() {
        return bikeserviceBookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getSbookingDate() {
        return sbookingDate;
    }

    public void setSbookingDate(LocalDate sbookingDate) {
        this.sbookingDate = sbookingDate;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public enums.status getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(enums.status serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    @Override
    public String toString() {
        return "bikeserviceBooking{" +
                "bikeserviceBookingId=" + bikeserviceBookingId +
                ", userId=" + userId +
                ", sbookingDate=" + sbookingDate +
                ", serviceDate=" + serviceDate +
                ", serviceStatus=" + serviceStatus +
                '}';
    }
}