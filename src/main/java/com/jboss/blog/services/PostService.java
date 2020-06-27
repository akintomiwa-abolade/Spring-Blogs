package com.jboss.blog.services;

import com.jboss.blog.models.Category;
import com.jboss.blog.models.Post;
import com.jboss.blog.models.User;
import com.jboss.blog.repository.CategoryRepository;
import com.jboss.blog.repository.PostRepository;
import com.jboss.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Post createPost(Post post){
        User user = userRepository.findById(post.getUser().getId()).orElse(null);
        Category cats = categoryRepository.findById(post.getCategory().getId()).orElse(null);
        post.setUser(user);
        post.setCategory(cats);
        return postRepository.save(post);
    }
    public Post fetchPost(Long id){
        return postRepository.findById(id).orElse(null);
    }
    public List<Post>findByUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        return postRepository.findPostByUser(user.getId());
    }
    public List<Post>fetchPosts(){
      return postRepository.findAll();
    }
    public List<Post>findByCategory(Long id){
        return postRepository.findByCategory(id);
    }

}
