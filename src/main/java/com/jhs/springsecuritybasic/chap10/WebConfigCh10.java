package com.jhs.springsecuritybasic.chap10;

import com.jhs.springsecuritybasic.chap10.repository.CustomCsrfTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RequiredArgsConstructor
public class WebConfigCh10 {

    private final CustomCsrfTokenRepository customCsrfTokenRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*http.csrf(c -> {
            c.ignoringAntMatchers("/ciao");
            c.csrfTokenRepository(customCsrfTokenRepository);
        });*/

        http.csrf().disable();
        http.cors(Customizer.withDefaults());

        http//.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll();

        /*http.formLogin()
                .defaultSuccessUrl("/main", true);*/
        return http.build();
    }



    /*@Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080")
                        .allowedMethods("GET", "POST");
            }
        };
    }*/
}
