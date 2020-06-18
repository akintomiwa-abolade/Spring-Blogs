package com.jboss.blog.controllers;

import com.jboss.blog.models.Comment;
import com.jboss.blog.models.Post;
import com.jboss.blog.models.User;
import com.jboss.blog.services.CommentService;
import com.jboss.blog.services.PostService;
import com.jboss.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/create-account")
    public User signUp(@RequestBody User user){
        return userService.createAccount(user);
    }
    @PostMapping("/create-post")
    public Post userSubmitPost(@RequestBody Post post){
        return postService.createPost(post);
    }
    @PostMapping("/post-comment")
    public ResponseEntity<Comment>userPosComment(@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.OK);
    }
    @GetMapping("/fetch-post/{id}")
    public List<Post> userPosts(@PathVariable Long id) {
        return postService.findByUser(id);
    }
}
