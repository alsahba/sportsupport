package com.sport.support.appuser.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeUserNameInfoRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

}
