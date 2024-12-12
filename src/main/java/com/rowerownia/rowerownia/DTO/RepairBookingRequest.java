package com.rowerownia.rowerownia.DTO;

public class RepairBookingRequest {
    private Integer userId;
    private String rbookingDate;
    private String repairDate;
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
