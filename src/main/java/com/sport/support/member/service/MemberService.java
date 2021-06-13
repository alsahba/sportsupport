package com.sport.support.member.service;

import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import com.sport.support.member.entity.Member;
import com.sport.support.member.entity.Membership;
import com.sport.support.member.entity.enumeration.MemberStatus;
import com.sport.support.member.messages.MemberErrorMessages;
import com.sport.support.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MembershipService membershipService;

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
        memberRepository.findById(member.getId()).ifPresentOrElse(memberDb -> {
            memberDb.update(member);
            memberRepository.save(memberDb);
        }, () -> {
            throw new RecordDoesNotExistException(MemberErrorMessages.MEMBER_DOES_NOT_EXIST);
        });
    }

    public void delete(Long id) {
        memberRepository.findById(id).ifPresentOrElse(memberRepository::delete, () -> {
            throw new RecordDoesNotExistException(MemberErrorMessages.MEMBER_DOES_NOT_EXIST);
        });
    }

    public void updateMemberStatus(Long id, MemberStatus memberStatus) {
        memberRepository.findById(id).ifPresentOrElse(memberDb -> {
            memberDb.setStatus(memberStatus);
            memberRepository.save(memberDb);
        }, () -> {
            throw new RecordDoesNotExistException(MemberErrorMessages.MEMBER_DOES_NOT_EXIST);
        });
    }

    public Long addMembership(Membership membership) {
        memberRepository.findById(membership.getMember().getId()).ifPresentOrElse(memberDb -> {
            membership.setMember(memberDb);
            membershipService.add(membership);
        }, () -> {
            throw new RecordDoesNotExistException(MemberErrorMessages.MEMBER_DOES_NOT_EXIST);
        });
        return membership.getId();
    }
}
