package com.sport.support.appuser.domain;

import com.sport.support.appuser.domain.vo.RoleId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
public class Role extends AbstractDomainObject<RoleId> {

   private String name;
   private Set<String> permissions;

}
