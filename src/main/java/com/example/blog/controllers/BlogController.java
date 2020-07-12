package com.example.blog.controllers;

import com.example.blog.models.Customer;
import com.example.blog.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @Autowired
    private CustomerRepository postRepository;


    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Customer> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "BlogMain";
    }

}
