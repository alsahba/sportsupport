package com.sport.support.infrastructure.exception;

public class RecordDoesNotExistException extends RuntimeException {

    public RecordDoesNotExistException(String message) {
        super(message);
    }
}
