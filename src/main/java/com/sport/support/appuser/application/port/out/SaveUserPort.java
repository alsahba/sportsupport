package com.sport.support.appuser.application.port.out;

import com.sport.support.appuser.domain.AppUser;
import com.sport.support.appuser.domain.Role;

public interface SaveUserPort {

   AppUser save(AppUser user);

   void update(Long id, String name, String surname);

   void update(Long id, String newPassword);

   void update(Long id, Role role);

}
