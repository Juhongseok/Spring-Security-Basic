package com.jhs.springsecuritybasic;

import com.jhs.springsecuritybasic.chap05.HelloController;
import com.jhs.springsecuritybasic.chap05.ProjectConfigCh05;
import com.jhs.springsecuritybasic.chap06.config.ProjectConfigCh06;
import com.jhs.springsecuritybasic.chap06.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@Import({/*ProjectConfigCh02.class,*/ WebAuthorizationConfigCh02.class})
//@Import(ProjectConfigCh03.class)
//@Import(ProjectConfigCh05.class)
//@Import({ProjectConfigCh06.class, WebConfig.class})
@ComponentScan("com.jhs.springsecuritybasic.chap09")
@EnableWebSecurity(debug = true)
public class SpringSecurityBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}
}
