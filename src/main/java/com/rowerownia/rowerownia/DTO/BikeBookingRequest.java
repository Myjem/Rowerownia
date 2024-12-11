package com.rowerownia.rowerownia.DTO;

import java.util.List;

public class BikeBookingRequest {
    private Integer userId;
    private String bookingDate;
    private String startDate;
    private String endDate;
    private List<Integer> bikeIds;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getBikeIds() {
        return bikeIds;
    }

    public void setBikeIds(List<Integer> bikeIds) {
        this.bikeIds = bikeIds;
    }





}
