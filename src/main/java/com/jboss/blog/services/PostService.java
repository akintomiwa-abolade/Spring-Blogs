package com.jboss.blog.services;

import com.jboss.blog.models.Post;
import com.jboss.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post){
        return postRepository.save(post);
    }
    public Post fetchPost(Long id){
        return postRepository.findById(id).orElse(null);
    }
    public List<Post>findByUser(Long id){
        return postRepository.findByUser(id);
    }
    public List<Post>fetchPosts(){
        return postRepository.findAll();
    }
    public List<Post>findByCategory(Long id){
        return postRepository.findByCategory(id);
    }


}
