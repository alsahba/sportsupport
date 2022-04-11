package com.sport.support.notification.application.port.in;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.notification.domain.ImportanceLevel;
import com.sport.support.notification.domain.Notification;
import lombok.Data;

@Data
public class AddNotificationCommand {

   private UserId userId;
   private String title;
   private String description;
   private ImportanceLevel level;

   public Notification toDomain() {
      return Notification.builder()
          .userId(userId)
          .title(title)
          .description(description)
          .level(level)
          .isRead(false)
          .build();
   }
}
