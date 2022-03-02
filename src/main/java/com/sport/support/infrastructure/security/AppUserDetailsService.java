package com.sport.support.infrastructure.security;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.appuser.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);
        AppUser appUser = user.orElseThrow(() -> new UsernameNotFoundException(username));

        return new AppUserPrincipal(
                appUser,
                appUser.getGrantedAuthorities(),
                true,
                true,
                true,
                true);
    }
}
