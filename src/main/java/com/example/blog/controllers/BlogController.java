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


    @GetMapping("/")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "BlogMain";
    }

    @GetMapping("/add")
    public String blogAdd(Model model) {
        return "BlogAdd";
    }

    @PostMapping("/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String description, Model model) {
        Post post = new Post(title, description);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id))
            return "redirect:/";

        ArrayList<Post> res = new ArrayList<>();
        postRepository.findById(id).ifPresent(res::add);
        model.addAttribute("post", res);
        return "BlogDetails";
    }

    @GetMapping("/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id))
            return "redirect:/";

        ArrayList<Post> res = new ArrayList<>();
        postRepository.findById(id).ifPresent(res::add);
        model.addAttribute("post", res);
        return "BlogEdit";
    }

    @PostMapping("/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String description, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setDescription(description);
        postRepository.save(post);
        return "redirect:/";
    }

    @PostMapping("/{id}/remove")
    public String blogRemove(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/";
    }


}
