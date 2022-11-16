package com.jhs.springsecuritybasic.chap06.service;

import com.jhs.springsecuritybasic.chap06.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {
    private final JpaUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails findUser = userDetailsService.loadUserByUsername(username);

        switch (findUser.getUser().getAlgorithm()) {
            case BCRYPT:
                return checkPassword(findUser, password, bCryptPasswordEncoder);
            case SCRYPT:
                return checkPassword(findUser, password, sCryptPasswordEncoder);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

    private Authentication checkPassword(CustomUserDetails findUser, String password, PasswordEncoder passwordEncoder) {
        if (passwordEncoder.matches(password, findUser.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    findUser.getUsername(),
                    findUser.getPassword(),
                    findUser.getAuthorities()
            );
        }
        throw new BadCredentialsException("Bad Credentials");
    }
}
