package com.sport.support.appuser.service;

import com.sport.support.appuser.AppUser;
import com.sport.support.appuser.messages.UserErrorMessages;
import com.sport.support.appuser.repository.AppUserRepository;
import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<Long, AppUser> redisTemplate;

    public Long register(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserRepository.save(user).getId();
    }

    public void update(Long id, String name, String surname) {
        AppUser appUser = retrieveById(id);
        appUser.update(name, surname);
        appUserRepository.save(appUser);
    }

    public void delete(Long id) {
        AppUser appUserDb = retrieveById(id);
        appUserRepository.delete(appUserDb);
    }

    @Cacheable(value = "user", key = "#id")
    public AppUser retrieveById(Long id) {
        return getFromCache(id).orElseGet(() -> getFromDatabase(id));
    }

    private Optional<AppUser> getFromCache(Long id) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(id));
    }


    private AppUser getFromDatabase(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new RecordDoesNotExistException(UserErrorMessages.USER_DOES_NOT_EXIST));
    }
}
