package com.sport.support.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final EmailPasswordAuthProvider emailPasswordAuthProvider;
    private final UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.ttl}")
    private long tokenTtl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtEmailPasswordAuthFilter(authenticationManager(), secretKey, tokenTtl), JwtUsernamePasswordAuthFilter.class)
                .addFilter(new JwtUsernamePasswordAuthFilter(authenticationManager(), secretKey, tokenTtl))
                .addFilterAfter(new JwtTokenVerifier(secretKey), JwtUsernamePasswordAuthFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/users/owner").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v3/api-docs",
                "/configuration/ui",
                "/swagger-ui/",
                "/swagger-resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthProvider)
                .authenticationProvider(emailPasswordAuthProvider);
    }
}
