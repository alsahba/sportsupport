package com.sport.support.infrastructure.common.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {
    SUCCESS("Completed successfully"),
    FAILURE("Failed to complete the request");

    private final String message;
}
