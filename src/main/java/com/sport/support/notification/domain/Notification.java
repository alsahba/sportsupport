package com.sport.support.notification.domain;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.notification.domain.vo.NotificationId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification extends AbstractDomainObject<NotificationId> {

   private UserId userId;
   private String title;
   private String description;
   private ImportanceLevel level;
   private boolean isRead;

}
