package com.sport.support.membership.application.port.out;

public interface DoesMembershipExistPort {
   boolean doesExist(Long userId, Long trainerId);
}
