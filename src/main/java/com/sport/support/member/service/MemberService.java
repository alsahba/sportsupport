package com.sport.support.member.service;

import com.sport.support.member.entity.Member;
import com.sport.support.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> retrieveAll() {
        return memberRepository.findAll();
    }

}
