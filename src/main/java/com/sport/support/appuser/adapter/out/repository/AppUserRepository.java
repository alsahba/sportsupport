package com.sport.support.appuser.adapter.out.repository;

import com.sport.support.appuser.adapter.out.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

   Optional<AppUser> findByUsername(String username);

   Optional<AppUser> findByEmail(String email);

   boolean existsByUsername(String username);

   void deleteByUsername(String username);

}
