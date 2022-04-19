package com.sport.support.notification.adapter.out.persistence;

import com.sport.support.notification.application.port.out.SaveNotificationPort;
import com.sport.support.notification.domain.Notification;
import com.sport.support.shared.common.annotations.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class NotificationPersistenceAdapter implements SaveNotificationPort {

   private final NotificationRepository repository;

   @Override
   public void save(Notification notification) {
      repository.save(new NotificationEntity(notification));
   }
}
