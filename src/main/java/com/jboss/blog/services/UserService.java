package com.jboss.blog.services;

import com.jboss.blog.models.Post;
import com.jboss.blog.models.User;
import com.jboss.blog.repository.PostRepository;
import com.jboss.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createAccount(User user){
        if(userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())){
            return user;
        }else{
            return userRepository.save(user);
        }
    }

}
