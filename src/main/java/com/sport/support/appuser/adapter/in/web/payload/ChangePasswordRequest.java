package com.sport.support.appuser.adapter.in.web.payload;

import com.sport.support.shared.common.annotations.validation.Password;
import lombok.Data;

@Data
public class ChangePasswordRequest {

   @Password
   private String oldPassword;

   @Password
   private String newPassword;

}
