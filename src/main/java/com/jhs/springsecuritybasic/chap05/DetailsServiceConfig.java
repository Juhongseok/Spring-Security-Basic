package com.jhs.springsecuritybasic.chap05;

import com.jhs.springsecuritybasic.chap03.InMemoryUserDetailsService;
import com.jhs.springsecuritybasic.chap03.user.SimpleUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DetailsServiceConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        SimpleUser user = new SimpleUser("John", "$2a$12$IGEcSvi9YLwlFTzZY5bzWObVxPh4QYQ17aN5pRVrp29cFJ65IbhXq", "READ");
        return new InMemoryUserDetailsService(List.of(user));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
