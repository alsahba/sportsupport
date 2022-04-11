package com.sport.support.shared.configuration.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KafkaTopic {

   CHANGE_BALANCE_TOPIC("change-balance-topic", "wallet"),
   NOTIFICATION_TOPIC("notification-topic", "notification");

   private final String topic;
   private final String groupId;

}
