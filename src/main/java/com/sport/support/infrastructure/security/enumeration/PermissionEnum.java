package com.sport.support.infrastructure.security.enumeration;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum PermissionEnum {
  USER_READ,
  USER_WRITE,
  MEMBER_WRITE,
  MEMBER_READ,
  MANAGER_WRITE,
  MANAGER_READ,
  ADMIN_READ,
  ADMIN_WRITE,
  BRANCH_READ,
  BRANCH_UPDATE,
  BRANCH_CREATE,
  TRAINER_READ,
  TRAINER_WRITE,
  EXERCISE_READ,
  EXERCISE_WRITE,
  PLAN_READ,
  PLAN_WRITE,
  MEMBERSHIP_READ,
  MEMBERSHIP_WRITE;

  public SimpleGrantedAuthority toGrantedAuthority() {
    return new SimpleGrantedAuthority(this.name());
  }
}
