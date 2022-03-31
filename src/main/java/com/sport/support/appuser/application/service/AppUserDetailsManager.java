package com.sport.support.appuser.application.service;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.appuser.application.port.in.command.ChangePasswordCommand;
import com.sport.support.appuser.application.port.in.command.ChangeUserNameCommand;
import com.sport.support.appuser.application.port.in.command.RegisterUserCommand;
import com.sport.support.appuser.application.port.in.command.UpdateRoleCommand;
import com.sport.support.appuser.application.port.in.usecase.*;
import com.sport.support.appuser.application.port.out.LoadAuthorityPort;
import com.sport.support.appuser.application.port.out.LoadUserPort;
import com.sport.support.appuser.application.port.out.RemoveUserPort;
import com.sport.support.appuser.application.port.out.SaveUserPort;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import com.sport.support.infrastructure.security.user.AppUserDetails;
import com.sport.support.wallet.application.port.in.command.CreateWalletCommand;
import com.sport.support.wallet.application.port.in.usecase.CreateWalletUC;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AppUserDetailsManager implements UserDetailsService, RegisterUserUC, ChangeUserNameUC,
    ChangePasswordUC, RemoveUserUC, LoadUserUC, UpdateRoleUC {

   private final CreateWalletUC createWalletUC;
   private final RemoveUserPort removeUserPort;
   private final SaveUserPort saveUserPort;
   private final LoadUserPort loadUserPort;
   private final PasswordEncoder passwordEncoder;
   private final LoadAuthorityPort loadAuthorityPort;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return new AppUserDetails(loadUserPort.loadByUsername(username));
   }

   @Override
   public AppUser loadById(Long id) {
      return loadUserPort.loadById(id);
   }

   @Override
   public AppUser loadByEmail(String email) {
      return loadUserPort.loadByEmail(email);
   }

   @Override
   public AppUser loadByUsername(String username) {
      return loadUserPort.loadByUsername(username);
   }

   @Override
   public UserDetails loadUserDetailsById(Long id) {
      return new AppUserDetails(loadUserPort.loadById(id));
   }

   @Override
   public void register(RegisterUserCommand command) {
      AppUser user = new AppUser(command);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRole(loadAuthorityPort.loadRole(RoleEnum.OWNER));
      saveUserPort.save(user);
      createWalletUC.create(new CreateWalletCommand(user.getId()));
   }

   @Override
   public void change(ChangeUserNameCommand command) {
      AppUser user = findById(command.getUserId());
      user.update(command.getName(), command.getSurname());
      saveUserPort.save(user);
   }

   @Override
   public void change(ChangePasswordCommand command) {
      AppUser user = findById(command.getId());

      if (!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
         throw new BadCredentialsException("Invalid credentials");
      }

      user.setPassword(command.getNewPassword());
      saveUserPort.save(user);
   }

   @Override
   public void remove(Long id) {
      removeUserPort.remove(id);
   }

   @Override
   public void update(UpdateRoleCommand command) {
      AppUser user = command.user();
      user.setRole(loadAuthorityPort.loadRole(command.role()));
      saveUserPort.save(user);
   }

   private AppUser findById(Long id) {
      return loadUserPort.loadById(id);
   }
}
