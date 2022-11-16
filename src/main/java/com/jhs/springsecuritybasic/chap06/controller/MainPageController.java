package com.jhs.springsecuritybasic.chap06.controller;

import com.jhs.springsecuritybasic.chap06.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainPageController {
    private final ProductService productService;

    @GetMapping("/main")
    public String main(Authentication e, Model model){
        model.addAttribute("username", e.getName());
        model.addAttribute("products", productService.findAll());
        return "main";
    }
}
