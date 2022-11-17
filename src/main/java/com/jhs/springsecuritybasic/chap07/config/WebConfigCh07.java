package com.jhs.springsecuritybasic.chap07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class WebConfigCh07 {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

        builder.authenticationProvider(authenticationProvider);
        return builder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .anyRequest()
                .permitAll();

        http.authorizeRequests()
                .anyRequest()
                .hasAuthority("WRITE");

        http.authorizeRequests()
                .anyRequest()
                .hasAnyAuthority("WRITE", "READ");

        http.authorizeRequests()
                .anyRequest()
                .access("hasAuthority('WRITE')");

        http.authorizeRequests()
                .anyRequest()
                .hasRole("MANAGER");

        http.authorizeRequests()
                .anyRequest()
                .denyAll()*/

        http.httpBasic();
        http.authorizeRequests()
                .mvcMatchers("/hello").hasRole("ADMIN")
                .mvcMatchers("/ciao").hasRole("MANAGER")
                .mvcMatchers(HttpMethod.GET, "/hola").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/hola").hasRole("MANAGER")
                .anyRequest().permitAll();

        http.csrf().disable(); //get 방식 이외의 http 메소드 호출 가능
        return http.build();
    }
}
