package com.jhs.springsecuritybasic;

import com.jhs.springsecuritybasic.chap10.ProjectConfigCh10;
import com.jhs.springsecuritybasic.chap10.WebConfigCh10;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import({/*ProjectConfigCh02.class,*/ WebAuthorizationConfigCh02.class})
//@Import(ProjectConfigCh03.class)
//@Import(ProjectConfigCh05.class)
//@Import({ProjectConfigCh06.class, WebConfig.class})
@Import({WebConfigCh10.class, ProjectConfigCh10.class})
@ComponentScan("com.jhs.springsecuritybasic.chap10")
//@EnableWebSecurity(debug = true)
public class SpringSecurityBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}
}
