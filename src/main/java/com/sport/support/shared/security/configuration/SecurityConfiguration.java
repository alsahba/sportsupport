package com.sport.support.shared.security.configuration;

import com.sport.support.shared.security.filter.BearerTokenAuthorizationFilter;
import com.sport.support.shared.security.filter.CustomAuthenticationFilter;
import com.sport.support.shared.security.provider.EmailPasswordAuthProvider;
import com.sport.support.shared.security.provider.UsernamePasswordAuthProvider;
import com.sport.support.appuser.application.service.AppUserDetailsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AppUserDetailsManager appUserDetailsManager;
  private final EmailPasswordAuthProvider emailPasswordAuthProvider;
  private final UsernamePasswordAuthProvider usernamePasswordAuthProvider;
  private final JwtProperties jwtProperties;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterAt(new CustomAuthenticationFilter(authenticationManager(), jwtProperties),
            BasicAuthenticationFilter.class)
        .addFilterAfter(new BearerTokenAuthorizationFilter(jwtProperties.getPrefix(),
                jwtProperties.getSecret(), appUserDetailsManager), CustomAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/users").permitAll()
        .anyRequest().authenticated();
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/v3/api-docs/**",
        "/configuration/ui",
        "/swagger-ui*/**",
        "/swagger-resources/**");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth
        .authenticationProvider(usernamePasswordAuthProvider)
        .authenticationProvider(emailPasswordAuthProvider);
  }
}
