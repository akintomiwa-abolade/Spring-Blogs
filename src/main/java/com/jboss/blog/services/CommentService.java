package com.jboss.blog.services;

import com.jboss.blog.models.Comment;
import com.jboss.blog.models.Post;
import com.jboss.blog.models.User;
import com.jboss.blog.repository.CommentRepository;
import com.jboss.blog.repository.PostRepository;
import com.jboss.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    public Comment createComment(Comment comment){
        Post post = postRepository.findById(comment.getPost().getId()).orElse(null);
        User user = userRepository.findById(comment.getUser().getId()).orElse(null);
        comment.setPost(post);
        comment.setUser(user);
        return commentRepository.save(comment);
    }
}
