package com.sport.support.shared.security.dto;

import lombok.Data;

@Data
public class LoginRequest {

  private String username;
  private String password;
  private String email;

}
