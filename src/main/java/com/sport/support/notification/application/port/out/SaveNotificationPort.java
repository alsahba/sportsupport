package com.sport.support.notification.application.port.out;

import com.sport.support.notification.domain.Notification;

public interface SaveNotificationPort {

   void save(Notification notification);

}
