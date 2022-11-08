package com.jhs.springsecuritybasic;

import com.jhs.springsecuritybasic.chap02.WebAuthorizationConfigCh02;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({/*ProjectConfigCh02.class,*/ WebAuthorizationConfigCh02.class})
public class SpringSecurityBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}

}
