/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.controllers;

import com.taoltech.finapp.models.User;
import com.taoltech.finapp.repositories.UserRepository;
import com.taoltech.finapp.requests.LoginDTO;
import com.taoltech.finapp.requests.RegisterDTO;
import com.taoltech.finapp.security.JwtUtil;
import com.taoltech.finapp.services.AccountService;
import com.taoltech.finapp.services.UserDetailsImp;
import java.util.HashMap;
import java.util.Map;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("auth")
public class AuthController extends BaseController {
    
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private JobScheduler jobScheduler;
    
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        
        /**
         * Auth
         * 
         */
        Authentication authentication;
        try {
            authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        }
        catch (AuthenticationException e) {
            return this.errResponse("Username OR Password not correct", e.getMessage());
        }
        
        // Details
        UserDetailsImp details = (UserDetailsImp) authentication.getPrincipal();
        
        // Token
        String token = this.jwtUtil.generateToken(authentication);
        
        //
        Map<String, Object> data = new HashMap<>();
        data.put("access_token", token);
        data.put("user", details.getUser());
        return this.okResponse("Login successful", data);
    }
    
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO dto) {
        
        /**
         * Prep Input
         */
        User user = dto.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        /**
         * Create User
         */
        final User createdModel = this.repository.save(user);
        
        /**
         * Generate Account Number
         * 
         */
        jobScheduler.enqueue(() -> accountService.generate(createdModel));
        
        //
        return this.okResponse("Account Created Successfully", createdModel);
    }
}
