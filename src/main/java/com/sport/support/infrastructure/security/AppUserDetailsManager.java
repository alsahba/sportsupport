package com.sport.support.infrastructure.security;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.appuser.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AppUserDetailsManager implements UserDetailsManager {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);
        return user.map(AppUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByEmail(email);
        return user.map(AppUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public void createUser(UserDetails user) {
        Assert.isInstanceOf(AppUserDetails.class, user, "Only AppUserDetails instances are supported");
        AppUserDetails appUserDetails = (AppUserDetails) user;
        AppUser appUser = appUserDetails.getAppUser();
        appUserRepository.save(appUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        Assert.isInstanceOf(AppUserDetails.class, user, "Only AppUserDetails instances are supported");
        AppUserDetails appUserDetails = (AppUserDetails) user;
        AppUser appUser = appUserDetails.getAppUser();
        appUserRepository.save(appUser);
    }

    @Override
    public void deleteUser(String username) {
        appUserRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null) {
            throw new AccessDeniedException("Can't change password as no Authentication object found in context for current user.");
        }
        String username = currentUser.getName();
        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        if (appUser.isPresent()) {
            AppUser user = appUser.get();
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                appUserRepository.save(user);
            } else {
                throw new AccessDeniedException("Wrong password");
            }
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public boolean userExists(String username) {
        return appUserRepository.existsByUsername(username);
    }
}
