package com.sport.support.appuser.application.port.in.usecase;

import com.sport.support.appuser.application.port.in.command.RegisterUserCommand;
import com.sport.support.appuser.domain.AppUser;

public interface RegisterUserUC {

   AppUser register(RegisterUserCommand command);

}
