package com.sport.support.appuser.controller;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.appuser.controller.dto.AddUserDTO;
import com.sport.support.appuser.controller.dto.UpdateUserDTO;
import com.sport.support.appuser.controller.dto.UserDetailDTO;
import com.sport.support.appuser.service.AppUserService;
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

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid AddUserDTO addUserDTO) {
        AppUser appUser = new AppUser(addUserDTO);
        appUserService.register(appUser);
        return new ResponseEntity<>(String.format("User with ID = %d created!", appUser.getId()), HttpStatus.CREATED);
    }

    // TODO: 2.03.2022 - to be deleted
    @PostMapping(value = "/owner")
    public ResponseEntity<String> addOwner(@RequestBody @Valid AddUserDTO addUserDTO) {
        appUserService.addOwner(new AppUser(addUserDTO));
        return new ResponseEntity<>("owner created!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<UserDetailDTO> get(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(new UserDetailDTO(appUserService.retrieveById(id)));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public ResponseEntity<String> update(@RequestBody @Valid UpdateUserDTO updateUserDTO,
                                         Authentication authentication) {
        Long id = Long.valueOf(authentication.getName());
        appUserService.update(id, updateUserDTO.getName(), updateUserDTO.getSurname());
        return new ResponseEntity<>(String.format("User with ID = %d updated!", id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public ResponseEntity<String> delete(Authentication authentication) {
        Long id = Long.valueOf(authentication.getName());
        appUserService.delete(id);
        return new ResponseEntity<>(String.format("User with ID = %d deleted!", id), HttpStatus.ACCEPTED);
    }
}
