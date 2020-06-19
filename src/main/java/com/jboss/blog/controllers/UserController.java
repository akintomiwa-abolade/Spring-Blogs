package com.jboss.blog.controllers;

import com.jboss.blog.models.Comment;
import com.jboss.blog.models.Post;
import com.jboss.blog.models.User;
import com.jboss.blog.services.CommentService;
import com.jboss.blog.services.PostService;
import com.jboss.blog.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@Api("General Operation pertaining to Users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String welcome(){
        return "index";
    }

    @PostMapping("/create-account")
    @ApiOperation("User create an Account")
    public User signUp(@RequestBody User user){
        String password = user.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encodedPassword);
        return userService.createUserAccount(user);
    }

    @PostMapping("/create-post")
    @ApiOperation("User create a blog post")
    public Post userSubmitPost(@Valid @RequestBody Post post){
        return postService.createPost(post);
    }

    @PostMapping("/post-comment")
    @ApiOperation("User create comment on a blog post")
    public ResponseEntity<Comment>userPosComment(@Valid @RequestBody Comment comment){
        return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.OK);
    }
    @GetMapping("/fetch-posts")
    public List<Post>viewPosts(){
        return postService.fetchPosts();
    }

    @GetMapping("/fetch-user-posts/{id}")
    public List<Post> userPosts(@PathVariable Long id) {
        return postService.findByUser(id);
    }
}
