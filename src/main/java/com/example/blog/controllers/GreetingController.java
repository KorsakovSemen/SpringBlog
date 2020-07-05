package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("title", "Главная страница");
        return "Greeting";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("about", "О нас");
        return "AboutUs";
    }




}
