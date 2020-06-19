package com.jboss.blog.services;

import com.jboss.blog.models.User;
import com.jboss.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService{
	String ROLE_PREFIX = "ROLE_";
	 @Autowired
	 private UserRepository userRepository;

	 @Override
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	     User user = userRepository.findByUsername(username);
	     if (user == null) {
	          throw new UsernameNotFoundException("User not found with username: " + username);
	     }
	     return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
	              new ArrayList<>());
	}
}
