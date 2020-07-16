package com.example.blog.controllers;

import com.example.blog.models.Customer;
import com.example.blog.models.Post;
import com.example.blog.repo.CustomerRepository;
import com.example.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "BlogMain";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "BlogAdd";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String description, Model model){
        Post post = new Post(title, description);
        postRepository.save(post);
        return "redirect:/blog";
    }

}
