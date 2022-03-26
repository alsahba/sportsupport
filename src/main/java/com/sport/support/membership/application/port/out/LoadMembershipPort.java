package com.sport.support.membership.application.port.out;

import com.sport.support.membership.adapter.out.persistence.entity.Membership;

import java.util.Optional;

public interface LoadMembershipPort {
   Optional<Membership> loadByUserId(Long userId);
}
