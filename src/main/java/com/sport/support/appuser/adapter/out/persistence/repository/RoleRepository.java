package com.sport.support.appuser.adapter.out.persistence.repository;

import com.sport.support.appuser.adapter.out.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

   Optional<RoleEntity> findByName(String name);

}
