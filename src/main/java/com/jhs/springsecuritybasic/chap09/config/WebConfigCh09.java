package com.jhs.springsecuritybasic.chap09.config;

import com.jhs.springsecuritybasic.chap09.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class WebConfigCh09 {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                new RequestValidationFilter(),
                BasicAuthenticationFilter.class
        );

        http.authorizeRequests()
                .anyRequest().permitAll();

        return http.build();
    }
}
