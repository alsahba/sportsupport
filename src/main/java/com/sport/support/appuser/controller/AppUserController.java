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
        Long id = appUserService.register(new AppUser(addUserDTO));
        return new ResponseEntity<>("User with ID = " + id + " created!", HttpStatus.CREATED);
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

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public ResponseEntity<String> update(@PathVariable @Min(1) Long id,
                                                @RequestBody @Valid UpdateUserDTO updateUserDTO) {
        appUserService.update(id, updateUserDTO.getName(), updateUserDTO.getSurname());
        return new ResponseEntity<>("User with ID = " + id + " updated!", HttpStatus.ACCEPTED);
    }
}
