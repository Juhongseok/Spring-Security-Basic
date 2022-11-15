package com.jhs.springsecuritybasic;

import com.jhs.springsecuritybasic.chap05.HelloController;
import com.jhs.springsecuritybasic.chap05.ProjectConfigCh05;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import({/*ProjectConfigCh02.class,*/ WebAuthorizationConfigCh02.class})
//@Import(ProjectConfigCh03.class)
@Import(ProjectConfigCh05.class)
@ComponentScan("com.jhs.springsecuritybasic.chap05")
public class SpringSecurityBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}

}
