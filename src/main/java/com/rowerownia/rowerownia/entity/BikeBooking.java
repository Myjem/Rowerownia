package com.rowerownia.rowerownia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "bikeBooking")
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
    @Column(name = "bikeBookingId", updatable = false)
    private Integer bikeBookingId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @Column(name = "bbookingDate", nullable = false)
    private LocalDate bbookingDate;
    @Column(name = "bstartDate", nullable = false)
    private LocalDate bstartDate;
    @Column(name = "bendDate", nullable = false)
    private LocalDate bendDate;
    @Column(name = "bikeStatus", nullable = false)
    private Enums.status bikeStatus;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "bikeBookingId"),
            inverseJoinColumns = @JoinColumn(name = "bikeId")
    )
    private List<Bike> bikes;

    public BikeBooking() {
    }

    public BikeBooking(User user, LocalDate localDate, LocalDate date, LocalDate localDate1, Enums.status pending) {
    }

    public BikeBooking(User user, LocalDate bbookingDate, LocalDate bstartDate, LocalDate bendDate, Enums.status bikeStatus, List<Bike> bikes) {
        this.user = user;
        this.bbookingDate = bbookingDate;
        this.bstartDate = bstartDate;
        this.bendDate = bendDate;
        this.bikeStatus = bikeStatus;
        this.bikes = bikes;
    }

    public Integer getBikeBookingId() {
        return bikeBookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    @Override
    public String toString() {
        return "bikeBooking{" +
                "bikeBookingId=" + bikeBookingId +
                ", user=" + user +
                ", bbookingDate=" + bbookingDate +
                ", bstartDate=" + bstartDate +
                ", bendDate=" + bendDate +
                ", bikeStatus=" + bikeStatus +
                ", bikes=" + bikes +
                '}';
    }
}
