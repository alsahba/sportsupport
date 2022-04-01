package com.sport.support.shared.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfiguration {

   @Value("${spring.redis.host}")
   private String host;

   @Value("${spring.redis.port}")
   private String port;

   @Bean
   public CacheManager cacheTimeoutManager(RedisConnectionFactory connectionFactory) {
      Duration expiration = Duration.ofSeconds(60);
      return RedisCacheManager.builder(connectionFactory)
          .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(expiration)).build();
   }

   @Bean
   public RedissonClient redissonClient() {
      Config config = new Config();
      config.useSingleServer().setAddress("redis://" + host + ":" + port);
      return Redisson.create(config);
   }
}
