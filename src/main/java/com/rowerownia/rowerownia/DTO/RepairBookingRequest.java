package com.rowerownia.rowerownia.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RepairBookingRequest {
    @NotNull
    private Integer userId;
    @NotNull
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Date must be in format yyyy-mm-dd")
    private String rbookingDate;
    @NotNull
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Date must be in format yyyy-mm-dd")
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
