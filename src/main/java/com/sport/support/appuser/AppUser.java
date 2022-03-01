package com.sport.support.appuser;

import com.sport.support.appuser.controller.dto.AddUserDTO;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "APP_USER")
@Data
@NoArgsConstructor
public class AppUser extends AbstractAuditableEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber;

    @Column(name = "E_MAIL", unique = true)
    private String email;

    public AppUser(AddUserDTO addUserDTO) {
        setName(addUserDTO.getName());
        setSurname(addUserDTO.getSurname());
        setEmail(addUserDTO.getEMail());
        setUsername(addUserDTO.getUsername());
        setPassword(addUserDTO.getPassword());
        setPhoneNumber(addUserDTO.getPhoneNumber());
    }

    public AppUser(Long id) {
        setId(id);
    }

    public void update(String name, String surname) {
        setName(name);
        setSurname(surname);
    }
}
