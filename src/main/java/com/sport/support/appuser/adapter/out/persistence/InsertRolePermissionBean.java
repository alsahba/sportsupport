package com.sport.support.appuser.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.entity.Permission;
import com.sport.support.appuser.adapter.out.persistence.entity.Role;
import com.sport.support.appuser.adapter.out.persistence.entity.RolePermission;
import com.sport.support.appuser.adapter.out.persistence.repository.PermissionRepository;
import com.sport.support.appuser.adapter.out.persistence.repository.RolePermissionRepository;
import com.sport.support.appuser.adapter.out.persistence.repository.RoleRepository;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InsertRolePermissionBean {

   // TODO: 28.03.2022 deleted after the role and permissions are finalized

   private final RolePermissionRepository rolePermissionRepository;

   public InsertRolePermissionBean(RoleRepository roleRepository, PermissionRepository permissionRepository, RolePermissionRepository rolePermissionRepository) {
      this.rolePermissionRepository = rolePermissionRepository;

      RoleEnum.getAll().forEach(roleEnum -> {
         Role role = roleRepository.findByName(roleEnum.name()).get();
         Set<Permission> permissions = permissionRepository.findByNameIn(roleEnum.getPermissions());
         insertRolePermission(role, permissions);
      });
   }

   private void insertRolePermission(Role role, Set<Permission> permissions) {
      permissions.forEach(p -> {
         RolePermission rolePermission = new RolePermission(role, p);
         rolePermissionRepository.save(rolePermission);
      });
   }
}
