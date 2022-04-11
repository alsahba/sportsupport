package com.sport.support.notification.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.notification.domain.ImportanceLevel;
import com.sport.support.notification.domain.Notification;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATION")
@Getter
@Setter
@NoArgsConstructor
class NotificationEntity extends AbstractAuditableEntity {

   @ManyToOne
   private AppUserEntity user;

   private String title;

   private String description;

   private ImportanceLevel level;

   private boolean isRead;

   public NotificationEntity(Notification notification) {
      setId(notification.getId());
      setTitle(notification.getTitle());
      setDescription(notification.getDescription());
      setLevel(notification.getLevel());
      setRead(notification.isRead());
   }

   public Notification toDomain() {
      return Notification.builder()
          .userId(new UserId(user.getId()))
          .title(title)
          .description(description)
          .level(level)
          .isRead(isRead)
          .build();
   }
}
