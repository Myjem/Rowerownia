package com.rowerownia.rowerownia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "bike_service")
public class bikeService {
    @Id
    @SequenceGenerator(
            name = "bikeService_sequence",
            sequenceName = "bikeService_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bikeService_sequence")
    private Integer bikeServiceId;
    private String serviceName;
    private Integer serviceTime;
    private Integer servicePrice;

    public bikeService() {
    }

    public bikeService(String serviceName, Integer serviceTime, Integer servicePrice) {
        this.serviceName = serviceName;
        this.serviceTime = serviceTime;
        this.servicePrice = servicePrice;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Integer getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Integer servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Integer getBikeServiceId() {
        return bikeServiceId;
    }

    @Override
    public String toString() {
        return "bikeService{" +
                "bikeServiceId=" + bikeServiceId +
                ", serviceName='" + serviceName + '\'' +
                ", serviceTime=" + serviceTime +
                ", servicePrice=" + servicePrice +
                '}';
    }
}
