package com.sport.support.appuser.application.service;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.appuser.domain.UserErrorMessages;
import com.sport.support.appuser.adapter.out.persistence.repository.AppUserRepository;
import com.sport.support.appuser.adapter.out.persistence.repository.PermissionRepository;
import com.sport.support.employee.adapter.out.persistence.Employee;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import com.sport.support.infrastructure.security.user.AppUserDetails;
import com.sport.support.wallet.application.port.in.command.CreateWalletCommand;
import com.sport.support.wallet.application.port.in.usecase.CreateWalletUC;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserDetailsManager implements UserDetailsManager {

   private final AppUserRepository appUserRepository;
   private final CreateWalletUC createWalletUC;
   private final PasswordEncoder passwordEncoder;
   private final PermissionRepository permissionRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<AppUser> user = appUserRepository.findByUsername(username);
      return user.map(AppUserDetails::new)
          .orElseThrow(() -> new UsernameNotFoundException(username));
   }

   public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
      Optional<AppUser> user = appUserRepository.findById(id);
      return user.map(AppUserDetails::new)
          .orElseThrow(() -> new UsernameNotFoundException(id.toString()));
   }

   public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
      Optional<AppUser> user = appUserRepository.findByEmail(email);
      return user.map(AppUserDetails::new)
          .orElseThrow(() -> new UsernameNotFoundException(email));
   }

   public void update(Long id, String name, String surname) {
      AppUser appUser = findById(id);
      appUser.update(name, surname);
      appUserRepository.save(appUser);
   }

   @Override
   @Transactional
   public void createUser(UserDetails userDetails) {
      AppUser user = ((AppUserDetails) userDetails).getAppUser();
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setPermissions(permissionRepository.findByNameIn(RoleEnum.OWNER.getPermissions()));
      appUserRepository.save(user);
      createWalletUC.create(new CreateWalletCommand(user.getId()));
   }

   @Override
   public void changePassword(String oldPassword, String newPassword) {
      Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
      if (currentUser == null) {
         throw new AccessDeniedException("Can't change password as no Authentication object found in context for current user.");
      }
      Long userId = Long.valueOf(currentUser.getName());
      AppUser user = findById(userId);

      if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
         throw new AccessDeniedException("Wrong password");
      }

      user.setPassword(newPassword);
      appUserRepository.save(user);

   }

   @Override
   public boolean userExists(String username) {
      return appUserRepository.existsByUsername(username);
   }

   public AppUser findById(Long id) {
      return appUserRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException(UserErrorMessages.ERROR_USER_IS_NOT_FOUND));
   }

   private AppUser findByUsername(String username) {
      return appUserRepository.findByUsername(username)
          .orElseThrow(() -> new EntityNotFoundException(UserErrorMessages.ERROR_USER_IS_NOT_FOUND));
   }

   public void updatePermissions(Employee employee) {
      AppUser user = findByUsername(employee.getUser().getUsername());
      user.setPermissions(permissionRepository.findByNameIn(employee.getType().getRole().getPermissions()));
      employee.setUser(user);
      appUserRepository.save(user);
   }

   public void delete(Long id) {
      AppUser appUserDb = findById(id);
      appUserRepository.delete(appUserDb);
   }

   @Override
   public void updateUser(UserDetails userDetails) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void deleteUser(String username) {
      throw new UnsupportedOperationException();
   }
}
