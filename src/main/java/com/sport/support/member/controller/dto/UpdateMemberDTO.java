package com.sport.support.member.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateMemberDTO {

    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String phoneNumber;

    private String eMail;
}
