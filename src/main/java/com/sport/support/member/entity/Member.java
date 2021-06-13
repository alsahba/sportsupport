package com.sport.support.member.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractSystemUser;
import com.sport.support.member.controller.dto.AddMemberDTO;
import com.sport.support.member.controller.dto.UpdateMemberDTO;
import com.sport.support.member.entity.enumeration.MemberStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
@Data
@NoArgsConstructor
public class Member extends AbstractSystemUser {

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public Member(AddMemberDTO addMemberDTO) {
        setStatus(MemberStatus.ACTIVE);
        setName(addMemberDTO.getName());
        setSurname(addMemberDTO.getSurname());
        setEMail(addMemberDTO.getEMail());
        setUsername(addMemberDTO.getUsername());
        setPassword(addMemberDTO.getPassword());
        setPhoneNumber(addMemberDTO.getPhoneNumber());
    }

    public Member(UpdateMemberDTO updateMemberDTO) {
        setId(updateMemberDTO.getId());
        setName(updateMemberDTO.getName());
        setSurname(updateMemberDTO.getSurname());
        setPhoneNumber(updateMemberDTO.getPhoneNumber());
    }

    public Member(Long id) {
        setId(id);
    }

    public void update(Member member) {
        setPhoneNumber(member.getPhoneNumber());
        setName(member.getName());
        setSurname(member.getSurname());
    }
}
