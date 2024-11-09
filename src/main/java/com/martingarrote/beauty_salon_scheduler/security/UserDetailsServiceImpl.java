package com.martingarrote.beauty_salon_scheduler.security;

import com.martingarrote.beauty_salon_scheduler.exceptions.user.EmailNotFoundException;
import com.martingarrote.beauty_salon_scheduler.user.User;
import com.martingarrote.beauty_salon_scheduler.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

private final UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).orElseThrow(EmailNotFoundException::new);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.mapAuthorityToSimpleGrantedAuthority()
        );
    }
}
