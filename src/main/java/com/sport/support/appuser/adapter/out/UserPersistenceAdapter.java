package com.sport.support.appuser.adapter.out;

import com.sport.support.appuser.adapter.out.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserPersistenceAdapter  {

   private final AppUserRepository appUserRepository;

}
