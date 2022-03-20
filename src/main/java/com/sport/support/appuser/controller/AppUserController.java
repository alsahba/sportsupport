package com.sport.support.appuser.controller;

import com.sport.support.appuser.controller.dto.AddUserRequest;
import com.sport.support.appuser.controller.dto.ChangeUserNameInfoRequest;
import com.sport.support.appuser.controller.dto.ChangeUserPasswordRequest;
import com.sport.support.appuser.controller.dto.UserDetailResponse;
import com.sport.support.appuser.entity.AppUser;
import com.sport.support.appuser.service.AppUserDetailsManager;
import com.sport.support.infrastructure.security.user.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class AppUserController {

   private final AppUserDetailsManager appUserDetailsManager;

   @PostMapping
   public ResponseEntity<String> register(@RequestBody @Valid AddUserRequest addUserRequest) {
      AppUser appUser = new AppUser(addUserRequest);
      appUserDetailsManager.createUser(new AppUserDetails(appUser));
      return new ResponseEntity<>(String.format("User with ID = %d created!", appUser.getId()), HttpStatus.CREATED);
   }

   @GetMapping(value = "/{id}")
   @PreAuthorize("hasAuthority('USER_READ')")
   public ResponseEntity<UserDetailResponse> get(@PathVariable @Min(1) Long id) {
      return ResponseEntity.ok(new UserDetailResponse(appUserDetailsManager.retrieveById(id)));
   }

   @PutMapping(value = "/name")
   @PreAuthorize("hasAuthority('USER_WRITE')")
   public ResponseEntity<String> changeUserNameInfo(@RequestBody @Valid ChangeUserNameInfoRequest request, Authentication authentication) {
      Long id = Long.valueOf(authentication.getName());
      appUserDetailsManager.update(id, request.getName(), request.getSurname());
      return new ResponseEntity<>(String.format("User with ID = %d updated!", id), HttpStatus.ACCEPTED);
   }

   @PutMapping(value = "/password")
   @PreAuthorize("hasAuthority('USER_WRITE')")
   public ResponseEntity<String> changePassword(@RequestBody @Valid ChangeUserPasswordRequest request) {
      appUserDetailsManager.changePassword(request.getOldPassword(), request.getNewPassword());
      return new ResponseEntity<>("Password is changed", HttpStatus.ACCEPTED);
   }

   @DeleteMapping
   @PreAuthorize("hasAuthority('USER_WRITE')")
   public ResponseEntity<String> delete(Authentication authentication) {
      Long id = Long.valueOf(authentication.getName());
      appUserDetailsManager.delete(id);
      return new ResponseEntity<>(String.format("User with ID = %d deleted!", id), HttpStatus.ACCEPTED);
   }
}
