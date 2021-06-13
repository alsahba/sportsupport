package com.sport.support.member.service;

import com.sport.support.member.entity.Membership;
import com.sport.support.member.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public void add(Membership membership) {
        if (membership.isAddable()) {
            membershipRepository.save(membership);
        }
    }
}
