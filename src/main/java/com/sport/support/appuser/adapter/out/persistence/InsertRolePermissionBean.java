package com.sport.support.appuser.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.entity.PermissionEntity;
import com.sport.support.appuser.adapter.out.persistence.entity.RoleEntity;
import com.sport.support.appuser.adapter.out.persistence.entity.RolePermissionEntity;
import com.sport.support.appuser.adapter.out.persistence.repository.PermissionRepository;
import com.sport.support.appuser.adapter.out.persistence.repository.RolePermissionRepository;
import com.sport.support.appuser.adapter.out.persistence.repository.RoleRepository;
import com.sport.support.shared.security.enumeration.RoleEnum;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InsertRolePermissionBean {

   // TODO: 28.03.2022 deleted after the role and permissions are finalized

   private final RolePermissionRepository rolePermissionRepository;

   public InsertRolePermissionBean(RoleRepository roleRepository, PermissionRepository permissionRepository, RolePermissionRepository rolePermissionRepository) {
      this.rolePermissionRepository = rolePermissionRepository;

      RoleEnum.getAll().forEach(roleEnum -> {
         RoleEntity roleEntity = roleRepository.findByName(roleEnum.name()).get();
         Set<PermissionEntity> permissionEntities = permissionRepository.findByNameIn(roleEnum.getPermissions());
         insertRolePermission(roleEntity, permissionEntities);
      });
   }

   private void insertRolePermission(RoleEntity roleEntity, Set<PermissionEntity> permissionEntities) {
      permissionEntities.forEach(p -> {
         RolePermissionEntity rolePermissionEntity = new RolePermissionEntity(roleEntity, p);
         rolePermissionRepository.save(rolePermissionEntity);
      });
   }
}
