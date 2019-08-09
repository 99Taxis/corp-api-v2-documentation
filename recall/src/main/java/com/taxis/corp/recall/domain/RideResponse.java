package com.taxis.corp.recall.domain;

public class RideResponse {

    private String rideId;

    private Boolean smsStartedSent;

    private Boolean smsDriverCanceledSent;

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public Boolean getSmsStartedSent() {
        return smsStartedSent;
    }

    public void setSmsStartedSent(Boolean smsStartedSent) {
        this.smsStartedSent = smsStartedSent;
    }

    public Boolean getSmsDriverCanceledSent() {
        return smsDriverCanceledSent;
    }

    public void setSmsDriverCanceledSent(Boolean smsDriverCanceledSent) {
        this.smsDriverCanceledSent = smsDriverCanceledSent;
    }
}
