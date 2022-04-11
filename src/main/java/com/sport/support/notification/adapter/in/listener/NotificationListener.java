package com.sport.support.notification.adapter.in.listener;

import com.sport.support.notification.application.port.in.AddNotificationCommand;
import com.sport.support.notification.application.port.in.AddNotificationUC;
import com.sport.support.shared.configuration.kafka.KafkaTopic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationListener {

   private final AddNotificationUC addNotificationUC;

   @KafkaListener(topics = "notification", groupId = "notification")
   public void listen(AddNotificationCommand command) {
      log.info("Received message in " + KafkaTopic.NOTIFICATION_TOPIC.getTopic());
      addNotificationUC.add(command);
   }
}
