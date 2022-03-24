package com.sport.support.appuser.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.repository.AppUserRepository;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter  {

   private final AppUserRepository appUserRepository;

}
