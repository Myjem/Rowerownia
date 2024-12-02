package com.rowerownia.rowerownia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table
public class BikeBooking {
    @Id
    @SequenceGenerator(
            name = "bikeBooking_sequence",
            sequenceName = "bikeBooking_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bikeBooking_sequence")
    private Integer bikeBookingId;
    private Integer userId;
    private LocalDate bbookingDate;
    private LocalDate bstartDate;
    private LocalDate bendDate;
    private Enums.status bikeStatus;
    @ElementCollection
    @CollectionTable(name = "bike_ids", joinColumns = @JoinColumn(name = "bikeBookingId"))
    @Column(name = "bikeId")
    private List<Integer> bikeId;

    public BikeBooking() {
    }

    public BikeBooking(int i, LocalDate localDate, LocalDate date, LocalDate localDate1, Enums.status pending) {
    }

    public BikeBooking(Integer userId, LocalDate bbookingDate, LocalDate bstartDate, LocalDate bendDate, Enums.status bikeStatus, List<Integer> bikeId) {
        this.userId = userId;
        this.bbookingDate = bbookingDate;
        this.bstartDate = bstartDate;
        this.bendDate = bendDate;
        this.bikeStatus = bikeStatus;
        this.bikeId = bikeId;
    }

    public Integer getBikeBookingId() {
        return bikeBookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getBbookingDate() {
        return bbookingDate;
    }

    public void setBbookingDate(LocalDate bbookingDate) {
        this.bbookingDate = bbookingDate;
    }

    public LocalDate getBstartDate() {
        return bstartDate;
    }

    public void setBstartDate(LocalDate bstartDate) {
        this.bstartDate = bstartDate;
    }

    public LocalDate getBendDate() {
        return bendDate;
    }

    public void setBendDate(LocalDate bendDate) {
        this.bendDate = bendDate;
    }

    public Enums.status getBikeStatus() {
        return bikeStatus;
    }

    public void setBikeStatus(Enums.status bikeStatus) {
        this.bikeStatus = bikeStatus;
    }

    public List<Integer> getBikeId() {
        return bikeId;
    }

    public void setBikeId(List<Integer> bikeId) {
        this.bikeId = bikeId;
    }

    @Override
    public String toString() {
        return "bikeBooking{" +
                "bikeBookingId=" + bikeBookingId +
                ", userId=" + userId +
                ", bbookingDate=" + bbookingDate +
                ", bstartDate=" + bstartDate +
                ", bendDate=" + bendDate +
                ", bikeStatus=" + bikeStatus +
                ", bikeId=" + bikeId +
                '}';
    }
}
