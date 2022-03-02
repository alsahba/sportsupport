package com.sport.support.infrastructure.security.enumeration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sport.support.infrastructure.security.enumeration.PermissionEnum.*;

public enum RoleEnum {
    USER(Set.of(USER_WRITE, USER_READ)),
    MEMBER(Set.of(MEMBER_WRITE, MEMBER_READ, USER_READ, USER_WRITE)),
    OWNER(Set.of(MANAGER_READ, MANAGER_WRITE, MEMBER_READ, MEMBER_WRITE, USER_READ, USER_WRITE, BRANCH_READ, BRANCH_WRITE)),
    MANAGER(Set.of(MEMBER_READ, MEMBER_WRITE, USER_READ, USER_WRITE, BRANCH_READ, BRANCH_WRITE));

    private final Set<PermissionEnum> permissions;

    RoleEnum(Set<PermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public Set<PermissionEnum> getPermissions() {
        return permissions;
    }

    public Set<String> getPermissionNames() {
        return permissions.stream().map(PermissionEnum::name).collect(Collectors.toSet());
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = getPermissions().stream().map(
                p -> new SimpleGrantedAuthority(p.name())
        ).collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
}
