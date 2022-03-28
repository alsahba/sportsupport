package com.sport.support.appuser.application.port.in.command;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddUserToBranchCommand {

   private final AppUser user;
   private final Long branchId;

}
