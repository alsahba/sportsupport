package com.sport.support.appuser.adapter.out.persistence.repository;

import com.sport.support.appuser.adapter.out.persistence.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

   Set<PermissionEntity> findByNameIn(Collection<String> names);

}
