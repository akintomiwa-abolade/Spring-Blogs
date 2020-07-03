package com.jboss.blog.services;

import com.jboss.blog.models.Post;
import com.jboss.blog.models.Role;
import com.jboss.blog.models.User;
import com.jboss.blog.repository.PostRepository;
import com.jboss.blog.repository.RoleRepository;
import com.jboss.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User createUserAccount(User user){
        if(userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())){
            return user;
        }else{
            Role role = roleRepository.findById(1L).orElse(null);
            user.setRole(role);
            user.setStatus(1L);
            return userRepository.save(user);
        }
    }
    public User createAdminAccount(User user){
        if(userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())){
            return user;
        }else{
            Role role = roleRepository.findById(3L).orElse(null);
            user.setRole(role);
            user.setStatus(1L);
            return userRepository.save(user);
        }
    }
    public List<User>findUsers(Long roleId){
        return userRepository.findUserByRole(roleId);
    }
    public User deactivateUser(Long id){
        User existingUser = userRepository.findById(id).orElse(null);
        existingUser.setStatus(0L);
        return userRepository.save(existingUser);
    }
    public User viewUser(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User updateAccount(Long id, User user){
        User realUser = userRepository.findById(id).orElse(null);
        realUser.setFullname(user.getFullname());
        realUser.setPhone(user.getPhone());
        return userRepository.save(realUser);
    }
    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "User with ID " + id + " Deleted Successfully";
    }

}
