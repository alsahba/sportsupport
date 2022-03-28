package com.sport.support.appuser.application.service;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.appuser.adapter.out.persistence.repository.PermissionRepository;
import com.sport.support.appuser.application.port.in.command.*;
import com.sport.support.appuser.application.port.in.usecase.*;
import com.sport.support.appuser.application.port.out.LoadUserPort;
import com.sport.support.appuser.application.port.out.RemoveUserPort;
import com.sport.support.appuser.application.port.out.SaveUserPort;
import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.branch.application.port.in.command.FindBranchQuery;
import com.sport.support.branch.application.port.in.usecase.FindBranchUC;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import com.sport.support.infrastructure.security.user.AppUserDetails;
import com.sport.support.wallet.application.port.in.command.CreateWalletCommand;
import com.sport.support.wallet.application.port.in.usecase.CreateWalletUC;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
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
    ChangePasswordUC, RemoveUserUC, LoadUserUC, UpdatePermissionUC, AddUserToBranchUC {

   private final CreateWalletUC createWalletUC;
   private final FindBranchUC findBranchUC;
   private final RemoveUserPort removeUserPort;
   private final SaveUserPort saveUserPort;
   private final LoadUserPort loadUserPort;
   private final PasswordEncoder passwordEncoder;
   private final PermissionRepository permissionRepository;

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
   public void register(RegisterUserCommand command) {
      AppUser user = new AppUser(command);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setPermissions(permissionRepository.findByNameIn(RoleEnum.OWNER.getPermissions()));
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
         throw new AccessDeniedException("Wrong password");
      }

      user.setPassword(command.getNewPassword());
      saveUserPort.save(user);
   }

   @Override
   public void remove(Long id) {
      removeUserPort.remove(id);
   }

   @Override
   public void update(UpdatePermissionCommand command) {
      AppUser user = command.getUser();
      user.setPermissions(permissionRepository.findByNameIn(command.getPermissions()));
      saveUserPort.save(user);
   }

   @Override
   public void addUserToBranch(AddUserToBranchCommand command) {
      AppUser user = command.getUser();
      Branch branch = findBranchUC.findById(new FindBranchQuery(command.getBranchId()));
      user.addToBranch(branch);
      saveUserPort.save(user);
   }

   private AppUser findById(Long id) {
      return loadUserPort.loadById(id);
   }
}
