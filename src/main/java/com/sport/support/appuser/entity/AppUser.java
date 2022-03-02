package com.sport.support.appuser.entity;

import com.sport.support.appuser.controller.dto.AddUserDTO;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "APP_USER")
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERMISSION_APP_USER",
            joinColumns = @JoinColumn(name = "APP_USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID")
    )
    private Set<Permission> permissions;

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

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return getPermissions()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toSet());
    }
}
