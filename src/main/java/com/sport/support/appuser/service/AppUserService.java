package com.sport.support.appuser.service;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.appuser.messages.UserErrorMessages;
import com.sport.support.appuser.repository.AppUserRepository;
import com.sport.support.appuser.repository.PermissionRepository;
import com.sport.support.infrastructure.exception.RecordIsNotFoundException;
import com.sport.support.infrastructure.security.configuration.AppPasswordEncoder;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppPasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;
    private final RedisTemplate<Long, AppUser> redisTemplate;

    @Transactional
    public void register(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPermissions(permissionRepository.findByNameIn(RoleEnum.OWNER.getPermissions()));
        appUserRepository.save(user);
    }

    public void update(Long id, String name, String surname) {
        AppUser appUser = retrieveById(id);
        appUser.update(name, surname);
        appUserRepository.save(appUser);
    }

    public void updatePermissions(Long id, RoleEnum role) {
        AppUser user = retrieveById(id);
        user.setPermissions(permissionRepository.findByNameIn(role.getPermissions()));
        appUserRepository.save(user);
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
                .orElseThrow(() -> new RecordIsNotFoundException(UserErrorMessages.ERROR_USER_IS_NOT_FOUND));
    }
}
