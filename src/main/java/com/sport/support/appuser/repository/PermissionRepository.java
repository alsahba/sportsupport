package com.sport.support.appuser.repository;

import com.sport.support.appuser.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Set<Permission> findByNameIn(Collection<String> names);
}
