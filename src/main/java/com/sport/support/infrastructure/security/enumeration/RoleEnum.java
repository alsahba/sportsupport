package com.sport.support.infrastructure.security.enumeration;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sport.support.infrastructure.security.enumeration.PermissionEnum.*;

public enum RoleEnum {
  USER(Set.of(USER_WRITE, USER_READ)),
  MEMBER(Set.of(MEMBER_WRITE, MEMBER_READ, USER_READ, USER_WRITE)),
  OWNER(Set.of(MANAGER_READ, MANAGER_WRITE, MEMBER_READ, MEMBER_WRITE, USER_READ, USER_WRITE, BRANCH_READ, BRANCH_WRITE)),
  MANAGER(Set.of(MEMBER_READ, MEMBER_WRITE, USER_READ, USER_WRITE, BRANCH_READ, BRANCH_WRITE)),
  TRAINER(Set.of(MEMBER_READ, MEMBER_WRITE, TRAINER_READ, TRAINER_WRITE, USER_READ, USER_WRITE));

  private final Set<PermissionEnum> permissions;

  RoleEnum(Set<PermissionEnum> permissions) {
    this.permissions = permissions;
  }

  public Set<String> getPermissions() {
    return permissions.stream().map(PermissionEnum::name).collect(Collectors.toSet());
  }
}
