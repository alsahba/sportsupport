package com.sport.support.appuser.adapter.out.persistence.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Role extends AbstractEntity {

   @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
   private Set<RolePermission> rolePermissions;

   private String name;

   public Set<Permission> getPermissions() {
      return rolePermissions.stream()
          .map(RolePermission::getPermission)
          .collect(Collectors.toSet());
   }

}
