package com.jboss.blog.controllers;

import com.jboss.blog.models.User;
import com.jboss.blog.payloads.JwtRequest;
import com.jboss.blog.payloads.JwtResponse;
import com.jboss.blog.repository.UserRepository;
import com.jboss.blog.security.JwtToken;
import com.jboss.blog.services.MyUserDetailsService;
import com.jboss.blog.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    
    // login new user
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        Optional<User> userData = userRepository.findByUsername(authenticationRequest.getUsername());

        final String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(userData.get().getId(), userData.get().getFullname(), userData.get().getEmail(), userData.get().getUsername(),
                userData.get().getPhone(), userData.get().getPassword(), userData.get().getStatus(), token));

    }

    @PostMapping("/register")
    @ApiOperation("User create an Account")
    public User signUp(@RequestBody User user){
        String password = user.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encodedPassword);
        return userService.createUserAccount(user);
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

