package com.sport.support.appuser.adapter.out.persistence.repository;

import com.sport.support.appuser.adapter.out.persistence.entity.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {
}
