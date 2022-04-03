package com.sport.support.appuser.adapter.out.persistence.entity;

import com.sport.support.appuser.domain.Role;
import com.sport.support.appuser.domain.vo.RoleId;
import com.sport.support.shared.abstractions.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "role")
@NoArgsConstructor
public class RoleEntity extends AbstractEntity {

   @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
   private Set<RolePermissionEntity> rolePermissions;

   private String name;

   public RoleEntity(Long id) {
      super(id);
   }

   public Set<PermissionEntity> getPermissions() {
      return Optional.ofNullable(rolePermissions)
          .map(rp -> rp.stream()
              .map(RolePermissionEntity::getPermission)
              .collect(Collectors.toSet()))
          .orElse(new HashSet<>());
   }

   public Role toDomain() {
      return Role.builder()
          .idVO(new RoleId(getId()))
          .name(name)
          .permissions(getPermissions().stream().map(PermissionEntity::getName).collect(Collectors.toSet()))
          .build();
   }

}
