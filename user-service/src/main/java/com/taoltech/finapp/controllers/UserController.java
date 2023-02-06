/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.controllers;

import com.taoltech.finapp.models.User;
import com.taoltech.finapp.repositories.UserRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    
    @Autowired
    private UserRepository repository;
    
    @GetMapping()
    public List<Object> list() {
        return null;
    }
    
    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        if(id.equalsIgnoreCase("me")) {
            return this.okResponse("Profile", getUser());
        }
        
        //
        Optional<User> optional = this.repository.findById(UUID.fromString(id));
        
        //
        return this.okResponse("Profile", optional.get());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Object input) {
        return null;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
