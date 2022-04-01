package com.sport.support.appuser.adapter.out.persistence.entity;

import com.sport.support.shared.abstractions.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "ROLE_PERMISSION")
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionEntity extends AbstractEntity {

   @ManyToOne
   private RoleEntity role;

   @ManyToOne
   private PermissionEntity permission;

}
