package com.sport.support.infrastructure.security.enumeration;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sport.support.infrastructure.security.enumeration.PermissionEnum.*;

public enum RoleEnum {
  USER(Set.of(USER_WRITE, USER_READ)),
  MEMBER(Set.of(MEMBERSHIP_WRITE, USER_READ, USER_WRITE, EXERCISE_READ, PLAN_READ)),
  OWNER(Set.of(MANAGER_READ, MANAGER_WRITE, MEMBER_READ, MEMBER_WRITE, USER_READ, USER_WRITE, BRANCH_READ, BRANCH_CREATE, BRANCH_UPDATE, TRAINER_READ, TRAINER_WRITE, PLAN_READ, PLAN_WRITE, EXERCISE_READ, EXERCISE_WRITE)),
  MANAGER(Set.of(MEMBER_READ, MEMBER_WRITE, USER_READ, USER_WRITE, BRANCH_READ, TRAINER_WRITE, TRAINER_READ, BRANCH_UPDATE)),
  TRAINER(Set.of(MEMBER_READ, MEMBER_WRITE, EXERCISE_WRITE, EXERCISE_READ, USER_READ, USER_WRITE, PLAN_READ, PLAN_WRITE));

  private final Set<PermissionEnum> permissions;

  RoleEnum(Set<PermissionEnum> permissions) {
    this.permissions = permissions;
  }

  public Set<String> getPermissions() {
    return permissions.stream().map(PermissionEnum::name).collect(Collectors.toSet());
  }

  public static Set<RoleEnum> getAll() {
    return Set.of(USER, MEMBER, OWNER, MANAGER, TRAINER);
  }
}
