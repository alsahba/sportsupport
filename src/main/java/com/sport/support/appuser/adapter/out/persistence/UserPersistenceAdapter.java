package com.sport.support.appuser.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.appuser.adapter.out.persistence.repository.AppUserRepository;
import com.sport.support.appuser.application.port.out.LoadUserPort;
import com.sport.support.appuser.application.port.out.RemoveUserPort;
import com.sport.support.appuser.application.port.out.SaveUserPort;
import com.sport.support.appuser.domain.UserErrorMessages;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements SaveUserPort, LoadUserPort, RemoveUserPort {

   private final AppUserRepository appUserRepository;

   @Override
   public void save(AppUser appUser) {
      appUserRepository.save(appUser);
   }

   @Override
   public AppUser loadByUsername(String username) {
      return appUserRepository.findByUsername(username)
          .orElseThrow(() -> new BusinessRuleException(UserErrorMessages.ERROR_USER_IS_NOT_FOUND));
   }

   @Override
   public AppUser loadByEmail(String email) {
      return appUserRepository.findByEmail(email)
          .orElseThrow(() -> new BusinessRuleException(UserErrorMessages.ERROR_USER_IS_NOT_FOUND));
   }

   @Override
   public AppUser loadById(Long id) {
      return appUserRepository.findById(id)
          .orElseThrow(() -> new BusinessRuleException(UserErrorMessages.ERROR_USER_IS_NOT_FOUND));
   }

   @Override
   public void remove(Long id) {
      appUserRepository.deleteById(id);
   }
}
