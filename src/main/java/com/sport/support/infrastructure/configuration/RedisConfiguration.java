package com.sport.support.infrastructure.configuration;

import com.sport.support.member.entity.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.awt.print.Book;


@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<Long, Member> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Long, Member> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

}
