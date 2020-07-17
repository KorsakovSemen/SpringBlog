package com.example.blog.controllers;

import com.example.blog.models.Customer;
import com.example.blog.models.Post;
import com.example.blog.repo.CustomerRepository;
import com.example.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "BlogMain";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "BlogAdd";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String description, Model model) {
        Post post = new Post(title, description);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id))
            return "redirect:/blog";

        ArrayList<Post> res = new ArrayList<>();
        postRepository.findById(id).ifPresent(res::add);
        model.addAttribute("post", res);
        return "BlogDetails";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id))
            return "redirect:/blog";

        ArrayList<Post> res = new ArrayList<>();
        postRepository.findById(id).ifPresent(res::add);
        model.addAttribute("post", res);
        return "BlogEdit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String description, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setDescription(description);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogRemove(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }


}
