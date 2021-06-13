package com.sport.support.member.controller.dto;

import com.sport.support.member.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDetailDTO {

    private String name;

    private String surname;

    private String phoneNumber;

    public MemberDetailDTO(Member member) {
        this.name = member.getName();
        this.surname = member.getSurname();
        this.phoneNumber = member.getPhoneNumber();
    }
}
