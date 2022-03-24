package com.sport.support.membership.application.service;

import com.sport.support.membership.adapter.out.persistence.MembershipHistory;
import com.sport.support.membership.adapter.out.persistence.MembershipHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipHistoryService {

    private final MembershipHistoryRepository membershipHistoryRepository;

    public void add(MembershipHistory membershipHistory) {
        membershipHistoryRepository.save(membershipHistory);
    }
}
