package com.sport.support.membership.adapter.in.web;

import com.sport.support.membership.adapter.in.web.payload.AddMembershipRequest;
import com.sport.support.membership.application.port.in.command.AddMembershipCommand;
import com.sport.support.membership.application.port.in.usecase.AddMembershipUC;
import com.sport.support.membership.application.port.in.usecase.CancelMembershipUC;
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

    // TODO: 28.03.2022 members can request for change of trainer
    //  after trainer approved the request, membership will be updated with new trainer

    private final AddMembershipUC addMembershipUC;
    private final CancelMembershipUC cancelMembershipUC;

    @PostMapping
    @PreAuthorize("hasAuthority('MEMBER_WRITE')")
    public ResponseEntity<String> add(
            @RequestBody @Valid AddMembershipRequest request,
            Principal principal) {
        addMembershipUC.add(new AddMembershipCommand(Long.valueOf(principal.getName()), request));
        return new ResponseEntity<>("Membership added!", HttpStatus.CREATED);
    }

    @PostMapping("/cancel")
    @PreAuthorize("hasAuthority('MEMBER_WRITE')")
    public ResponseEntity<String> cancel(Authentication authentication) {
        cancelMembershipUC.cancel(Long.valueOf(authentication.getName()));
        return new ResponseEntity<>("Membership canceled", HttpStatus.ACCEPTED);
    }
}
