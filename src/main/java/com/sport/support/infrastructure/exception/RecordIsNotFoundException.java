package com.sport.support.infrastructure.exception;

public class RecordIsNotFoundException extends RuntimeException {

    public RecordIsNotFoundException(String message) {
        super(message);
    }
}
