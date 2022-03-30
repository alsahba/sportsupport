package com.sport.support.membership.application.port.out;

import com.sport.support.membership.domain.Membership;

public interface SaveMembershipPort {

   Membership save(Membership membership);

   void update(Membership membership);

}
