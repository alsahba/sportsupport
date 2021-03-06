package com.sport.support.membership.adapter.in.web;

import com.sport.support.shared.abstractions.adapters.web.AbstractController;
import com.sport.support.shared.common.web.Response;
import com.sport.support.membership.adapter.in.web.payload.AddMembershipRequest;
import com.sport.support.membership.adapter.in.web.payload.MembershipResponse;
import com.sport.support.membership.application.port.in.command.AddMembershipCommand;
import com.sport.support.membership.application.port.in.usecase.AddMembershipUC;
import com.sport.support.membership.application.port.in.usecase.CancelMembershipUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberships")
public class MembershipController extends AbstractController {

    // TODO: 28.03.2022 members can request for change of trainer
    //  after trainer approved the request, membership will be updated with new trainer

    private final AddMembershipUC addMembershipUC;
    private final CancelMembershipUC cancelMembershipUC;

    @PostMapping
    @PreAuthorize("hasAuthority('USER_WRITE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<MembershipResponse> add(@RequestBody @Valid AddMembershipRequest request, Principal principal) {
        var membership = addMembershipUC.add(new AddMembershipCommand(Long.valueOf(principal.getName()), request));
        return respond(new MembershipResponse(membership));
    }

    @PostMapping("/cancel")
    @PreAuthorize("hasAuthority('MEMBERSHIP_WRITE')")
    @ResponseStatus(HttpStatus.OK)
    public Response<Long> cancel(Authentication authentication) {
        var userId = Long.valueOf(authentication.getName());
        cancelMembershipUC.cancel(userId);
        return respond(userId);
    }
}
