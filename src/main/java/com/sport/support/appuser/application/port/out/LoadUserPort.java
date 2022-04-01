package com.sport.support.appuser.application.port.out;

import com.sport.support.appuser.domain.AppUser;

public interface LoadUserPort {

   AppUser loadByUsername(String username);

   AppUser loadByEmail(String email);

   AppUser loadById(Long id);

}
