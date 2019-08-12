package com.taxis.corp.recall.domain;

import java.util.List;

public class RideRequest {

    private Long employeeID;

    private RideAddressModel from;

    private RideAddressModel to;

    private String phoneNumber;

    private Long costCenterID;

    private String categoryID;

    private Long projectID;

    private String notes;

    private List<String> optionals;

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public RideAddressModel getFrom() {
        return from;
    }

    public void setFrom(RideAddressModel from) {
        this.from = from;
    }

    public RideAddressModel getTo() {
        return to;
    }

    public void setTo(RideAddressModel to) {
        this.to = to;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCostCenterID() {
        return costCenterID;
    }

    public void setCostCenterID(Long costCenterID) {
        this.costCenterID = costCenterID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<String> optionals) {
        this.optionals = optionals;
    }
}
