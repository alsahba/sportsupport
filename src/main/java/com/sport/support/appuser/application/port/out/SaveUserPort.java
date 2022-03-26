package com.sport.support.appuser.application.port.out;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;

public interface SaveUserPort {

   void save(AppUser appUser);

}
