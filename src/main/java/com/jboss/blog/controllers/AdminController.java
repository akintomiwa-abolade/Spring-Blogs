package com.jboss.blog.controllers;

import com.jboss.blog.models.Role;
import com.jboss.blog.models.User;
import com.jboss.blog.repository.RoleRepository;
import com.jboss.blog.services.AdminService;
import com.jboss.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
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
    public Role createRoles(@Valid @RequestBody Role role){
        return adminService.createRole(role);
    }

    @PostMapping("/register-admin")
    public User addAdmin(@Valid @RequestBody User user){
        return userService.createAdminAccount(user);
    }

    @GetMapping("/view-users/{id}")
    public List<User> viewUsers(@PathVariable Long id){
        return userService.findUsers(id);
    }
}
