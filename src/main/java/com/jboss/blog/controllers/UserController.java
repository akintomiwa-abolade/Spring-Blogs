package com.jboss.blog.controllers;

import com.jboss.blog.models.Post;
import com.jboss.blog.models.User;
import com.jboss.blog.services.PostService;
import com.jboss.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping("/create-account")
    public User signUp(@RequestBody User user){
        return userService.createAccount(user);
    }
    @PostMapping("/create-post")
    public Post userSubmitPost(@RequestBody Post post){
        return postService.createPost(post);
    }
    @GetMapping("/fetch-post/{id}")
    public List<Post> userPosts(Long id){
        return postService.findByUser(id);
    }
}
