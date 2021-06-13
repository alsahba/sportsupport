package com.sport.support.member.specifications;

import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import com.sport.support.infrastructure.specifications.AbstractSpecification;
import com.sport.support.member.entity.Member;
import com.sport.support.member.messages.MemberErrorMessages;

public class MemberExistsSpecification extends AbstractSpecification<Member> {

    @Override
    public boolean isSatisfiedBy(Member member) {
        if (member.getId() == null) {
            throw new RecordDoesNotExistException(MemberErrorMessages.MEMBER_DOES_NOT_EXIST);
        }
        return true;
    }
}
