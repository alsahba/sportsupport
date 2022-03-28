package com.sport.support.appuser.application.port.in.usecase;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoadUserUC {

   AppUser loadById(Long id);

   AppUser loadByEmail(String email);

   AppUser loadByUsername(String username);

   UserDetails loadUserDetailsById(Long id);

}
