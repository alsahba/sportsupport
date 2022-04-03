package com.sport.support.appuser.domain;

import com.sport.support.appuser.domain.vo.RoleId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Role extends AbstractDomainObject<RoleId> {

   private String name;
   private Set<String> permissions;

   @Builder
   public Role(RoleId idVO, String name, Set<String> permissions) {
      super(idVO);
      this.name = name;
      this.permissions = permissions;
   }
}
