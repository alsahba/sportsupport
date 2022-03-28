package com.sport.support.membership.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindMembershipQuery {

   private Long userId;
   private Long trainerId;

}
