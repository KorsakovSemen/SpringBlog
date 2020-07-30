package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GreetingController {


    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("about", "О нас");
        return "AboutUs";
    }




}
