package com.sport.support.infrastructure.security;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}
