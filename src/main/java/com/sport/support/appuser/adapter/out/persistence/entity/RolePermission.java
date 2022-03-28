package com.sport.support.appuser.adapter.out.persistence.entity;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission extends AbstractEntity {

   @ManyToOne
   private Role role;

   @ManyToOne
   private Permission permission;

}
