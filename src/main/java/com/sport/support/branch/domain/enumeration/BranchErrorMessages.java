package com.sport.support.branch.domain.enumeration;

import com.sport.support.shared.exception.BusinessRuleErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BranchErrorMessages implements BusinessRuleErrorMessage {

    ERROR_BRANCH_IS_NOT_FOUND("SSEB-1", "ERROR_BRANCH_IS_NOT_FOUND"),
    ERROR_BRANCH_QUOTA_IS_EMPTY("SSEB-2", "ERROR_BRANCH_QUOTA_IS_EMPTY"),
    ERROR_BRANCH_PAYMENT_INFO_IS_NOT_FOUND("SSEB-3", "ERROR_BRANCH_PAYMENT_INFO_IS_NOT_FOUND"),
    ERROR_BRANCH_UNAUTHORIZED_UPDATE("SSEB-4", "ERROR_BRANCH_UNAUTHORIZED_UPDATE");

    private final String code;
    private final String message;

}
