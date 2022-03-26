package com.sport.support.appuser.application.port.in.usecase;

import com.sport.support.appuser.application.port.in.command.UpdatePermissionCommand;

public interface UpdatePermissionUC {
    void update(UpdatePermissionCommand command);
}
