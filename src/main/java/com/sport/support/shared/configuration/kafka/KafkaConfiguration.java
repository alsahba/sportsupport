package com.sport.support.shared.configuration.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfiguration {

   @Value(value = "${kafka.bootstrapAddress}")
   private String bootstrapAddress;

   @Bean
   public KafkaAdmin kafkaAdmin() {
      Map<String, Object> configs = new HashMap<>();
      configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
      return new KafkaAdmin(configs);
   }

   @Bean
   public ProducerFactory<String, Object> producerFactory() {
      Map<String, Object> properties = new HashMap<>();
      properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
      properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
      properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
      properties.put(ProducerConfig.ACKS_CONFIG, "all");
      properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
      return new DefaultKafkaProducerFactory<>(properties);
   }

   @Bean
   public KafkaTemplate<String, Object> kafkaTemplate() {
      return new KafkaTemplate<>(producerFactory());
   }

   @Bean
   public ConsumerFactory<String, Object> consumerFactory() {
      Map<String, Object> properties = new HashMap<>();
      properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
      properties.put(ConsumerConfig.GROUP_ID_CONFIG, "todo");
      properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
      properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
      properties.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
      return new DefaultKafkaConsumerFactory<>(properties);
   }

   @Bean
   public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
      ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
      factory.setConsumerFactory(consumerFactory());
      return factory;
   }
}
