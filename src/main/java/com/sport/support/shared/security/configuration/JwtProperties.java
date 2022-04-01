package com.sport.support.shared.security.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class JwtProperties {

  private final String secret;
  private final Long expireTime;
  private final Long refreshTime;
  private final String prefix;

}
