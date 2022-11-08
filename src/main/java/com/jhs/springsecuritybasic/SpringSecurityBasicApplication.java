package com.jhs.springsecuritybasic;

import com.jhs.springsecuritybasic.chap02.WebAuthorizationConfigCh02;
import com.jhs.springsecuritybasic.chap03.ProjectConfigCh03;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import({/*ProjectConfigCh02.class,*/ WebAuthorizationConfigCh02.class})
@Import(ProjectConfigCh03.class)
public class SpringSecurityBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}

}
