package com.sport.support.shared.configuration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfiguration {

   @Bean
   public NewTopic changeBalanceTopic() {
      return new NewTopic(KafkaTopic.CHANGE_BALANCE_TOPIC.getTopic(), 1, (short) 1);
   }

}
