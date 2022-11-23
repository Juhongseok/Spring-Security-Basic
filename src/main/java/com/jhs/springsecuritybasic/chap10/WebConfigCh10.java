package com.jhs.springsecuritybasic.chap10;

import com.jhs.springsecuritybasic.chap10.repository.CustomCsrfTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@RequiredArgsConstructor
public class WebConfigCh10 {

    private final CustomCsrfTokenRepository customCsrfTokenRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> {
            c.ignoringAntMatchers("/ciao");
            c.csrfTokenRepository(customCsrfTokenRepository);
        });

        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll();
/*
        http.formLogin()
                .defaultSuccessUrl("/main", true);*/
        return http.build();
    }
}
