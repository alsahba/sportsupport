package com.sport.support.appuser.application.port.in.usecase;

import com.sport.support.appuser.application.port.in.command.ChangeUserNameCommand;

public interface ChangeUserNameUC {

   void change(ChangeUserNameCommand command);

}
