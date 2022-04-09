package com.sport.support.shared.configuration.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KafkaTopic {

   CHANGE_BALANCE_TOPIC("change-balance-topic", "wallet");

   private final String topic;
   private final String groupId;

}
