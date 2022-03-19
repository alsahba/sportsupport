package com.sport.support.appuser.entity;

import com.sport.support.appuser.controller.dto.AddUserRequest;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class AppUser extends AbstractAuditableEntity {

    private String name;

    private String surname;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERMISSION_APP_USER",
            joinColumns = @JoinColumn(name = "APP_USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID")
    )
    private Set<Permission> permissions;

    public AppUser(AddUserRequest addUserRequest) {
        setName(addUserRequest.getName());
        setSurname(addUserRequest.getSurname());
        setEmail(addUserRequest.getEMail());
        setUsername(addUserRequest.getUsername());
        setPassword(addUserRequest.getPassword());
        setPhoneNumber(addUserRequest.getPhoneNumber());
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
