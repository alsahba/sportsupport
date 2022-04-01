package com.sport.support.appuser.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.repository.PermissionRepository;
import com.sport.support.appuser.adapter.out.persistence.repository.RolePermissionRepository;
import com.sport.support.appuser.adapter.out.persistence.repository.RoleRepository;
import com.sport.support.appuser.application.port.out.LoadAuthorityPort;
import com.sport.support.appuser.domain.Role;
import com.sport.support.shared.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.shared.security.enumeration.RoleEnum;

@PersistenceAdapter
public class AuthorityPersistenceAdapter implements LoadAuthorityPort {

   private final PermissionRepository permissionRepository;
   private final RoleRepository roleRepository;
   private final RolePermissionRepository rolePermissionRepository;

   public AuthorityPersistenceAdapter(PermissionRepository permissionRepository, RoleRepository roleRepository, RolePermissionRepository rolePermissionRepository) {
      this.permissionRepository = permissionRepository;
      this.roleRepository = roleRepository;
      this.rolePermissionRepository = rolePermissionRepository;
   }

   @Override
   public Role loadRole(RoleEnum role) {
      return roleRepository.findByName(role.name())
          .orElseThrow(() -> new IllegalArgumentException("Role not found")).toDomain();
   }
}
