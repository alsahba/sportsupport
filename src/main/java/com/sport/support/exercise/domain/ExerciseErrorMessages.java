package com.sport.support.exercise.domain;

import com.sport.support.infrastructure.exception.BusinessRuleErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExerciseErrorMessages implements BusinessRuleErrorMessage {

   ERROR_EXERCISE_IS_NOT_FOUND("SSEEX-1", "ERROR_EXERCISE_IS_NOT_FOUND");

   private final String code;
   private final String message;

}