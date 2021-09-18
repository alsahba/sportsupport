package com.sport.support.member.service;

import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import com.sport.support.infrastructure.specifications.SpecificationFactory;
import com.sport.support.infrastructure.specifications.SpecificationName;
import com.sport.support.member.entity.Member;
import com.sport.support.member.entity.Membership;
import com.sport.support.member.entity.enumeration.MemberStatus;
import com.sport.support.member.messages.MemberErrorMessages;
import com.sport.support.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MembershipService membershipService;
    private final SpecificationFactory<AbstractEntity> specificationFactory;

    public Long add(Member member) {
        return memberRepository.save(member).getId();
    }

    public List<Member> retrieveAll() {
        return memberRepository.findAll();
    }

    public Member retrieveById(Long id) {
        return memberRepository.findById(id).orElse(new Member());
    }

    public void update(Member member) {
        Member memberDb = memberRepository.findById(member.getId()).get();
        if (specificationFactory.execute(SpecificationName.MEMBER_EXISTS, member)) {
            memberDb.update(member);
            memberRepository.save(memberDb);
        }
    }

    public void delete(Long id) {
        Member memberDb = memberRepository.findById(id).get();
        if (specificationFactory.execute(SpecificationName.MEMBER_EXISTS, memberDb)) {
            memberRepository.delete(memberDb);
        }
    }

    public void updateMemberStatus(Long id, MemberStatus memberStatus) {
        Member memberDb = memberRepository.findById(id).get();
        if (specificationFactory.execute(SpecificationName.MEMBER_EXISTS, memberDb)) {
            memberDb.setStatus(memberStatus);
            memberRepository.save(memberDb);
        }
    }

    public Long addMembership(Membership membership) {
        Member memberDb = memberRepository.findById(membership.getMember().getId()).get();
        if (specificationFactory.execute(SpecificationName.MEMBER_EXISTS, memberDb)) {
            membership.setMember(memberDb);
            membershipService.add(membership);
        }
        return membership.getId();
    }
}
