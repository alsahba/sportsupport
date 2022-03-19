package com.sport.support.infrastructure.security.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class JwtProperties {

  private String secret;
  private Long expireTime;
  private Long refreshTime;
  private String prefix;

}
