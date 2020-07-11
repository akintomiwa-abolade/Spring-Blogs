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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8091")
@RestController
@RequestMapping("/api/v1")
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

    @PostMapping("/create-post")
    @ApiOperation("User create a blog post")
    @PreAuthorize("hasRole(1)")
    public Post userSubmitPost(@Valid @RequestBody Post post){
        return postService.createPost(post);
    }

    @PostMapping("/post-comment")
    @ApiOperation("User create comment on a blog post.")
    @PreAuthorize("hasRole(1)")
    public ResponseEntity<Comment>userPosComment(@Valid @RequestBody Comment comment){
        return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.OK);
    }
    @GetMapping("/fetch-posts")
    @PreAuthorize("hasRole(1)")
    public List<Post>viewPosts(){
        return postService.fetchPosts();
    }

    @GetMapping("/fetch-user-posts/{id}")
    @PreAuthorize("hasRole(1)")
    public List<Post> userPosts(@PathVariable Long id) {
        return postService.findByUser(id);
    }

    @GetMapping("/edit-user-info/{id}")
    @PreAuthorize("hasRole(1)")
    public User editUser(@PathVariable Long id){
        return userService.viewUser(id);
    }

    @PutMapping("/update-account")
    @PreAuthorize("hasRole(1)")
    public User updateAccount(@PathVariable Long id, @RequestBody User user){
        return userService.updateAccount(id, user);
    }
}
