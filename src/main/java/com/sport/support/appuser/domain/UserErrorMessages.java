package com.sport.support.appuser.domain;

import com.sport.support.shared.exception.BusinessRuleErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorMessages implements BusinessRuleErrorMessage {

    ERROR_USER_IS_NOT_FOUND("SSEU-1", "ERROR_USER_IS_NOT_FOUND");

    private final String code;
    private final String message;

}
