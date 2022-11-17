package com.jhs.springsecuritybasic.chap07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class ProjectConfigCh07 {
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager managers = new InMemoryUserDetailsManager();
        /*UserDetails user1 = User.withUsername("john")
                .password("12345")
                .authorities("READ")
                .build();

        UserDetails user2 = User.withUsername("jane")
                .password("12345")
                .authorities("WRITE")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("12345")
                .authorities("ROLE_ADMIN")
                .build();

        UserDetails manager = User.withUsername("manager")
                .password("12345")
                .authorities("ROLE_MANAGER")
                .build();*/

        UserDetails admin = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .build();

        UserDetails manager = User.withUsername("jane")
                .password("12345")
                .roles("MANAGER")
                .build();

//        managers.createUser(user1);
//        managers.createUser(user2);
        managers.createUser(admin);
        managers.createUser(manager);
        return managers;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
