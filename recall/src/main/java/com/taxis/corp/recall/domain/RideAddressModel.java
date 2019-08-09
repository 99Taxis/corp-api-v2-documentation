package com.taxis.corp.recall.domain;

public class RideAddressModel {

    private Double latitude;

    private Double longitude;

    private String street;

    public RideAddressModel() {

    }

    public RideAddressModel(Double latitude, Double longitude, String street) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
