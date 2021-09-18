package com.sport.support.member.specifications;

import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import com.sport.support.infrastructure.specifications.AbstractSpecification;
import com.sport.support.infrastructure.specifications.SpecificationName;
import com.sport.support.member.entity.Member;
import com.sport.support.member.messages.MemberErrorMessages;
import com.sport.support.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberExistsSpecification extends AbstractSpecification<Member> {

    @Override
    public boolean isSatisfiedBy(Member member) {
        if (member == null) {
            throw new RecordDoesNotExistException(MemberErrorMessages.MEMBER_DOES_NOT_EXIST);
        }
        return true;
    }

    @Override
    public SpecificationName getName() {
        return SpecificationName.MEMBER_EXISTS;
    }
}
