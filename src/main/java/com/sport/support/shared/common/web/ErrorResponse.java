package com.sport.support.shared.common.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

   private final String code;
   private final String message;

}
