package com.sport.support.appuser.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import com.sport.support.appuser.adapter.out.persistence.entity.RoleEntity;
import com.sport.support.appuser.adapter.out.persistence.repository.AppUserRepository;
import com.sport.support.appuser.application.port.out.LoadUserPort;
import com.sport.support.appuser.application.port.out.RemoveUserPort;
import com.sport.support.appuser.application.port.out.SaveUserPort;
import com.sport.support.appuser.domain.AppUser;
import com.sport.support.appuser.domain.Role;
import com.sport.support.appuser.domain.enumeration.UserErrorMessages;
import com.sport.support.shared.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements SaveUserPort, LoadUserPort, RemoveUserPort {

   private final AppUserRepository appUserRepository;

   @Override
   public AppUser save(AppUser user) {
      var entity = new AppUserEntity(user);
      return appUserRepository.save(entity).toDomain();
   }

   @Override
   public void update(Long id, String name, String surname) {
      var entity = findById(id);
      entity.setName(name);
      entity.setSurname(surname);
      appUserRepository.save(entity);
   }

   @Override
   public void update(Long id, String newPassword) {
      var entity = findById(id);
      entity.setPassword(newPassword);
      appUserRepository.save(entity);
   }

   @Override
   public void update(Long id, Role role) {
      var entity = findById(id);
      entity.setRole(new RoleEntity(role.getId()));
      appUserRepository.save(entity);
   }

   @Override
   public AppUser loadByUsername(String username) {
      return appUserRepository.findByUsername(username)
          .orElseThrow(() -> new BusinessRuleException(UserErrorMessages.ERROR_USER_IS_NOT_FOUND)).toDomain();
   }

   @Override
   public AppUser loadByEmail(String email) {
      return appUserRepository.findByEmail(email)
          .orElseThrow(() -> new BusinessRuleException(UserErrorMessages.ERROR_USER_IS_NOT_FOUND)).toDomain();
   }

   @Override
   public AppUser loadById(Long id) {
      return findById(id).toDomain();
   }

   private AppUserEntity findById(Long id) {
      return appUserRepository.findById(id)
          .orElseThrow(() -> new BusinessRuleException(UserErrorMessages.ERROR_USER_IS_NOT_FOUND));
   }

   @Override
   public void remove(Long id) {
      appUserRepository.deleteById(id);
   }
}
