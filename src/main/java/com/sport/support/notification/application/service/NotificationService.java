package com.sport.support.notification.application.service;

import com.sport.support.notification.application.port.in.AddNotificationCommand;
import com.sport.support.notification.application.port.in.AddNotificationUC;
import com.sport.support.notification.application.port.out.SaveNotificationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService implements AddNotificationUC {

   private final SaveNotificationPort saveNotificationPort;

   @Override
   public void add(AddNotificationCommand command) {
      saveNotificationPort.save(command.toDomain());
   }
}
