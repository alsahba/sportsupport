package com.sport.support.membership.application.port.out;

public interface DoesMembershipExistPort {

   boolean doesExistByUserAndTrainer(Long userId, Long trainerId);

   boolean doesExistByUser(Long userId);

}
