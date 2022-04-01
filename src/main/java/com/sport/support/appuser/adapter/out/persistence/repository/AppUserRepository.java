package com.sport.support.appuser.adapter.out.persistence.repository;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {

   Optional<AppUserEntity> findByUsername(String username);

   Optional<AppUserEntity> findByEmail(String email);

}
