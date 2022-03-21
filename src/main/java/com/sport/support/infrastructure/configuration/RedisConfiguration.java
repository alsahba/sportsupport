package com.sport.support.infrastructure.configuration;

import com.sport.support.infrastructure.common.Money;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfiguration {

   @Bean(name = "walletBalanceCache")
   public RedisTemplate<Long, Money> redisWalletBalanceTemplate(RedisConnectionFactory connectionFactory) {
      RedisTemplate<Long, Money> template = new RedisTemplate<>();
      template.setConnectionFactory(connectionFactory);
      return template;
   }

   @Bean(name = "branchQuotaCache")
   public RedisTemplate<Long, Integer> redisBranchQuotaTemplate(RedisConnectionFactory connectionFactory) {
      RedisTemplate<Long, Integer> template = new RedisTemplate<>();
      template.setConnectionFactory(connectionFactory);
      template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Integer.class));
      template.setKeySerializer(new Jackson2JsonRedisSerializer<>(Long.class));
      return template;
   }

   @Bean
   public CacheManager timeoutCacheManager(RedisConnectionFactory connectionFactory) {
      Duration expiration = Duration.ofSeconds(60);
      return RedisCacheManager.builder(connectionFactory)
          .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(expiration)).build();
   }
}
