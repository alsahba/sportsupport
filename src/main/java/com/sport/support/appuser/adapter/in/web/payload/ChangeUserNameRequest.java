package com.sport.support.appuser.adapter.in.web.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChangeUserNameRequest {

    @NotBlank
    @Size(min = 1, max = 64)
    private String name;

    @NotBlank
    @Size(min = 1, max = 64)
    private String surname;

}
