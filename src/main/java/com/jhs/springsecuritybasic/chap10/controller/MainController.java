package com.jhs.springsecuritybasic.chap10.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/main")
    public String main(){
        return "main10";
    }

//    @CrossOrigin("http://localhost:8080")
    @ResponseBody
    @PostMapping("/test")
    public String test(){
        log.info("TEST METHOD CALLED");
        return "HELLO";
    }
}
