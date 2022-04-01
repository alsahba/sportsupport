package com.sport.support.appuser.adapter.in.web;

import com.sport.support.appuser.adapter.in.web.payload.AddUserRequest;
import com.sport.support.appuser.adapter.in.web.payload.ChangePasswordRequest;
import com.sport.support.appuser.adapter.in.web.payload.ChangeUserNameRequest;
import com.sport.support.appuser.adapter.in.web.payload.UserResponse;
import com.sport.support.appuser.application.port.in.command.ChangePasswordCommand;
import com.sport.support.appuser.application.port.in.command.ChangeUserNameCommand;
import com.sport.support.appuser.application.port.in.command.RegisterUserCommand;
import com.sport.support.appuser.application.port.in.usecase.*;
import com.sport.support.appuser.domain.AppUser;
import com.sport.support.shared.abstractions.adapters.web.AbstractController;
import com.sport.support.shared.common.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class AppUserController extends AbstractController {

   private final RegisterUserUC registerUserUC;
   private final ChangeUserNameUC changeUserNameUC;
   private final ChangePasswordUC changePasswordUC;
   private final RemoveUserUC removeUserUC;
   private final LoadUserUC loadUserUC;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Response<UserResponse> register(@RequestBody @Valid AddUserRequest addUserRequest) {
      AppUser user = registerUserUC.register(new RegisterUserCommand(addUserRequest));
      return respond(new UserResponse(user));
   }

   @GetMapping(value = "/{id}")
   @PreAuthorize("hasAuthority('USER_READ')")
   @ResponseStatus(HttpStatus.OK)
   public Response<UserResponse> get(@PathVariable @Positive Long id) {
      return respond(new UserResponse(loadUserUC.loadById(id)));
   }

   @PutMapping(value = "/name")
   @PreAuthorize("hasAuthority('USER_WRITE')")
   @ResponseStatus(HttpStatus.OK)
   public Response<Long> changeUserNameInfo(@RequestBody @Valid ChangeUserNameRequest request, Principal principal) {
      Long id = getUserId(principal);
      changeUserNameUC.change(new ChangeUserNameCommand(id, request));
      return respond(id);
   }

   @PutMapping(value = "/password")
   @PreAuthorize("hasAuthority('USER_WRITE')")
   @ResponseStatus(HttpStatus.OK)
   public Response<Long> changePassword(@RequestBody @Valid ChangePasswordRequest request, Principal principal) {
      Long id = getUserId(principal);
      changePasswordUC.change(new ChangePasswordCommand(id, request));
      return respond(id);
   }

   @DeleteMapping
   @PreAuthorize("hasAuthority('USER_WRITE')")
   @ResponseStatus(HttpStatus.OK)
   public Response<Long> delete(Authentication authentication) {
      Long id = Long.valueOf(authentication.getName());
      removeUserUC.remove(id);
      return respond(id);
   }

   private Long getUserId(Principal principal) {
      return Long.valueOf(principal.getName());
   }
}
