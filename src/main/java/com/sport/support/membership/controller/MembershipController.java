package com.sport.support.membership.controller;

import com.sport.support.membership.controller.dto.AddMembershipRequest;
import com.sport.support.membership.entity.Membership;
import com.sport.support.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('MEMBER_WRITE')")
    public ResponseEntity<String> add(
            @RequestBody @Valid AddMembershipRequest addMembershipDTO,
            Authentication authentication) {
        Membership membership = new Membership(Long.valueOf(authentication.getName()), addMembershipDTO);
        membershipService.add(membership);
        return new ResponseEntity<>(String.format("Membership added, id = %d", membership.getId()), HttpStatus.CREATED);
    }

    @PostMapping("/cancel")
    @PreAuthorize("hasAuthority('MEMBER_WRITE')")
    public ResponseEntity<String> cancel(Authentication authentication) {
        membershipService.cancel(Long.valueOf(authentication.getName()));
        return new ResponseEntity<>("Membership canceled", HttpStatus.ACCEPTED);
    }
}
