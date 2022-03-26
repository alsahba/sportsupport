package com.sport.support.appuser.application.port.in.usecase;

import com.sport.support.appuser.application.port.in.command.RegisterUserCommand;

public interface RegisterUserUC {
   void register(RegisterUserCommand command);
}
