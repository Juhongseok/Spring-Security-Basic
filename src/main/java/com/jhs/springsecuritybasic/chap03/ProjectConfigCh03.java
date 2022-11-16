package com.jhs.springsecuritybasic.chap03;

import com.jhs.springsecuritybasic.chap03.user.SimpleUser;
import com.jhs.springsecuritybasic.chap06.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class ProjectConfigCh03 {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        SimpleUser user = new SimpleUser("John", "12345", "READ");
        return new InMemoryUserDetailsService(List.of(user));
//        return new JpaUserDetailsManager(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
