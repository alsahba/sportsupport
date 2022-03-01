package com.sport.support.membership.controller;

import com.sport.support.membership.controller.dto.AddMembershipDTO;
import com.sport.support.membership.entity.Membership;
import com.sport.support.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberships")
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping
    public ResponseEntity<String> add(
            @RequestBody @Valid AddMembershipDTO addMembershipDTO,
            Authentication authentication) {
        Membership membership = new Membership(Long.valueOf(authentication.getName()), addMembershipDTO);
        membershipService.add(membership);
        return new ResponseEntity<>(String.format("Membership added, id = %d", membership.getId()),
                HttpStatus.CREATED);
    }

}
