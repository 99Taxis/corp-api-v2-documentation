package com.taxis.corp.recall.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RideNotFoundException extends RuntimeException {

    public RideNotFoundException() {
        this("Ride not found");
    }

    public RideNotFoundException(final String message) {
        super(message);
    }
}
