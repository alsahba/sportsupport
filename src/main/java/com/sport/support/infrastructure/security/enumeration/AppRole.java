package com.sport.support.infrastructure.security.enumeration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sport.support.infrastructure.security.enumeration.AppPermission.*;

public enum AppRole {
    TEST(Set.of(READ_MEMBER)),
    ADMIN(Set.of(READ_MEMBER, CREATE_MEMBER));

    private final Set<AppPermission> permissions;

    AppRole(Set<AppPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = getPermissions().stream().map(
                p -> new SimpleGrantedAuthority(p.name())
        ).collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
}
