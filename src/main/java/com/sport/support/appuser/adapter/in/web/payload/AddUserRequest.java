package com.sport.support.appuser.adapter.in.web.payload;

import com.sport.support.infrastructure.common.annotations.validation.MobilePhoneNumber;
import com.sport.support.infrastructure.common.annotations.validation.Password;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddUserRequest {

    @NotBlank
    @Size(min = 1, max = 64)
    private String name;

    @NotBlank
    @Size(min = 1, max = 64)
    private String surname;

    @NotBlank
    @Size(min = 1, max = 16)
    private String username;

    @Password
    private String password;

    @MobilePhoneNumber
    private String phoneNumber;

    @Email
    private String email;
}
