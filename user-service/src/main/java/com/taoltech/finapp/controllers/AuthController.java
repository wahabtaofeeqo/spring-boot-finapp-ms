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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/auth")
public class AuthController extends BaseController {
    
    @Autowired
    UserRepository repository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        
        User user = this.repository.findByEmail(dto.getUsername());
        
        return null;
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
        user = this.repository.save(user);
        
        /**
         * Generate Account Number
         * 
         */
        
        
        //
        return this.okResponse("Account Created Successfully", user);
    }
}
