package com.sport.support.membership.adapter.in.web;

import com.sport.support.membership.adapter.in.web.payload.AddMembershipRequest;
import com.sport.support.membership.adapter.out.persistence.Membership;
import com.sport.support.membership.application.service.MembershipService;
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
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberships")
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping
    @PreAuthorize("hasAuthority('MEMBER_WRITE')")
    public ResponseEntity<String> add(
            @RequestBody @Valid AddMembershipRequest addMembershipDTO,
            Principal principal) {
        Membership membership = new Membership(Long.valueOf(principal.getName()), addMembershipDTO);
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
