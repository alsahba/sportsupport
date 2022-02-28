package com.sport.support.appuser.controller.dto;

import com.sport.support.appuser.AppUser;
import lombok.Data;

@Data
public class UserDetailDTO {

    private String name;
    private String surname;
    private String email;

    public UserDetailDTO(AppUser appUser) {
        name = appUser.getName();
        surname = appUser.getSurname();
        email = appUser.getEmail();
    }

}
