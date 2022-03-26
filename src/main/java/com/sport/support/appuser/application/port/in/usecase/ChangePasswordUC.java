package com.sport.support.appuser.application.port.in.usecase;

import com.sport.support.appuser.application.port.in.command.ChangePasswordCommand;

public interface ChangePasswordUC {
    void change(ChangePasswordCommand command);
}
