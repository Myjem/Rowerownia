package com.rowerownia.rowerownia.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RepairBookingRequest {
    @NotNull
    private Integer userId;
    @NotNull
    @Size(min = 10, max = 10)
    private String rbookingDate;
    @NotNull
    @Size(min = 10, max = 10)
    private String repairDate;
    @NotNull
    private Integer repairId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRbookingDate() {
        return rbookingDate;
    }

    public void setRbookingDate(String rbookingDate) {
        this.rbookingDate = rbookingDate;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public Integer getRepairId() {
        return repairId;
    }

    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }

}
