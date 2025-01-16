package com.rowerownia.rowerownia.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class BikeBookingRequest {
    @NotNull
    private Integer userId;
    @NotNull
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Date must be in format yyyy-mm-dd")
    private String bookingDate;
    @NotNull
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Date must be in format yyyy-mm-dd")
    private String startDate;
    @NotNull
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Date must be in format yyyy-mm-dd")
    private String endDate;
    @NotNull
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
