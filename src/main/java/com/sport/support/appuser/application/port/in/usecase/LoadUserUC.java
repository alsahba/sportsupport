package com.sport.support.appuser.application.port.in.usecase;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;

public interface LoadUserUC {

   AppUser loadById(Long id);

}
