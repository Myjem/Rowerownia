package com.rowerownia.rowerownia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "bike")
public class Bike {
    @Id
    @SequenceGenerator(
            name = "bike_sequence",
            sequenceName = "bike_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bike_sequence")
    @Column(name = "bikeId", updatable = false)
    private Integer bikeId;
    @Column(name = "bikeName", nullable = false)
    private String bikeName;
    @Column(name = "bikeSize", nullable = false, length = 2)
    private String bikeSize;
    @Column(name = "bikePrice", nullable = false, length = 4)
    @PositiveOrZero(message = "Price must be positive or zero")
    private Integer bikePrice;
    @Column(name = "isBroken", nullable = false)
    private boolean isBroken;

    public Bike() {
    }

    public Bike(String bikeName, String bikeSize, Integer bikePrice, boolean isBroken) {
        this.bikeName = bikeName;
        this.bikeSize = bikeSize;
        this.bikePrice = bikePrice;
        this.isBroken = isBroken;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken ;
    }

    public String getBikeSize() {
        return bikeSize;
    }

    public void setBikeSize(String bikeSize) {
        this.bikeSize = bikeSize;
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public Integer getBikePrice() {
        return bikePrice;
    }

    public void setBikePrice(Integer bikePrice) {
        this.bikePrice = bikePrice;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "bikeId=" + bikeId +
                ", bikeName='" + bikeName + '\'' +
                ", size='" + bikeSize + '\'' +
                ", bikePrice=" + bikePrice +
                ", isBroken=" + isBroken +
                '}';
    }

}
