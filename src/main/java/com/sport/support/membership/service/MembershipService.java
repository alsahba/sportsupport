package com.sport.support.membership.service;

import com.sport.support.branch.service.BranchService;
import com.sport.support.membership.entity.Membership;
import com.sport.support.membership.entity.MembershipHistory;
import com.sport.support.membership.repository.MembershipHistoryRepository;
import com.sport.support.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final MembershipHistoryRepository membershipHistoryRepository;
    private final BranchService branchService;

    @Transactional
    public void add(Membership membership) {
        membership.setBranch(branchService.retrieveById(membership.getBranch().getId()));
        // TODO: 1.03.2022 wallet development wrt branch membership payment
        membershipRepository.save(membership);
        membershipHistoryRepository.save(new MembershipHistory(membership));
    }
}
