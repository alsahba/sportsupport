package com.sport.support.membership.service;

import com.sport.support.membership.entity.MembershipHistory;
import com.sport.support.membership.repository.MembershipHistoryRepository;
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
