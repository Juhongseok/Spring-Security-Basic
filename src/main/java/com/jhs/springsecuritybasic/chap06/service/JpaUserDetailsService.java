package com.jhs.springsecuritybasic.chap06.service;

import com.jhs.springsecuritybasic.chap06.model.CustomUserDetails;
import com.jhs.springsecuritybasic.chap06.model.Users;
import com.jhs.springsecuritybasic.chap06.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users findUser = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Problem during authentication"));
        return new CustomUserDetails(findUser);
    }
}
