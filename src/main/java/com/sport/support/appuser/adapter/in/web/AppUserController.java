package com.sport.support.appuser.adapter.in.web;

import com.sport.support.appuser.adapter.in.web.payload.AddUserRequest;
import com.sport.support.appuser.adapter.in.web.payload.ChangePasswordRequest;
import com.sport.support.appuser.adapter.in.web.payload.ChangeUserNameRequest;
import com.sport.support.appuser.adapter.in.web.payload.UserDetailResponse;
import com.sport.support.appuser.application.port.in.command.ChangePasswordCommand;
import com.sport.support.appuser.application.port.in.command.ChangeUserNameCommand;
import com.sport.support.appuser.application.port.in.command.RegisterUserCommand;
import com.sport.support.appuser.application.port.in.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.security.Principal;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class AppUserController {

   private final RegisterUserUC registerUserUC;
   private final ChangeUserNameUC changeUserNameUC;
   private final ChangePasswordUC changePasswordUC;
   private final RemoveUserUC removeUserUC;
   private final LoadUserUC loadUserUC;

   @PostMapping
   public ResponseEntity<String> register(@RequestBody @Valid AddUserRequest addUserRequest) {
      registerUserUC.register(new RegisterUserCommand(addUserRequest));
      return new ResponseEntity<>("User is created!", HttpStatus.CREATED);
   }

   @GetMapping(value = "/{id}")
   @PreAuthorize("hasAuthority('USER_READ')")
   public ResponseEntity<UserDetailResponse> get(@PathVariable @Min(1) Long id) {
      return ResponseEntity.ok(new UserDetailResponse(loadUserUC.loadById(id)));
   }

   @PutMapping(value = "/name")
   @PreAuthorize("hasAuthority('USER_WRITE')")
   public ResponseEntity<String> changeUserNameInfo(@RequestBody @Valid ChangeUserNameRequest request, Authentication authentication) {
      Long id = Long.valueOf(authentication.getName());
      changeUserNameUC.change(new ChangeUserNameCommand(id, request));
      return new ResponseEntity<>(String.format("User with ID = %d updated!", id), HttpStatus.ACCEPTED);
   }

   @PutMapping(value = "/password")
   @PreAuthorize("hasAuthority('USER_WRITE')")
   public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordRequest request, Principal principal) {
      changePasswordUC.change(new ChangePasswordCommand(Long.valueOf(principal.getName()), request));
      return new ResponseEntity<>("Password is changed", HttpStatus.ACCEPTED);
   }

   @DeleteMapping
   @PreAuthorize("hasAuthority('USER_WRITE')")
   public ResponseEntity<String> delete(Authentication authentication) {
      Long id = Long.valueOf(authentication.getName());
      removeUserUC.remove(id);
      return new ResponseEntity<>(String.format("User with ID = %d deleted!", id), HttpStatus.ACCEPTED);
   }
}
