package com.sport.support.appuser.adapter.in.web.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeUserNameInfoRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

}
