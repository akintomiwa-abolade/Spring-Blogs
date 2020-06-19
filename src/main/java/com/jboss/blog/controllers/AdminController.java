package com.jboss.blog.controllers;

import com.jboss.blog.models.Role;
import com.jboss.blog.models.User;
import com.jboss.blog.repository.RoleRepository;
import com.jboss.blog.services.AdminService;
import com.jboss.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/add-role")
    public Role createRoles(Role role){
        return adminService.createRole(role);
    }

    @PostMapping("/register-admin")
    public User addAdmin(User user){
        return userService.createAdminAccount(user);
    }

    @GetMapping("/view-users/{id}")
    public List<User> viewUsers(Long id){
        return userService.findUsers(id);
    }
}
