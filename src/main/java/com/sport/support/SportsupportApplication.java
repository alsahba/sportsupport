package com.sport.support;

import com.sport.support.shared.security.configuration.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
@EnableJpaAuditing
public class SportsupportApplication {

   public static void main(String[] args) {
      SpringApplication.run(SportsupportApplication.class, args);
   }

}
