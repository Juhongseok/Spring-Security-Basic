package com.jhs.springsecuritybasic.chap05;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class HelloController {
    @GetMapping("/home")
    public String hello(){
        System.out.println("hello");
        return "home.html";
    }
}
