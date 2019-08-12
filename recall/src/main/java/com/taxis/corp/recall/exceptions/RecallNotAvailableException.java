package com.taxis.corp.recall.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class RecallNotAvailableException extends RuntimeException {

    public RecallNotAvailableException() {
        this("Recall is not available for this ride");
    }

    public RecallNotAvailableException(final String message) {
        super(message);
    }
}
