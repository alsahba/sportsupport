package com.sport.support.appuser.controller.dto;

import com.sport.support.infrastructure.common.annotations.Password;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeUserPasswordRequest {

   @Password
   private String oldPassword;

   @Password
   private String newPassword;

}
