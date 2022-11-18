/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.services;

import com.taoltech.finapp.models.User;
import com.taoltech.finapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UserRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = this.repository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username does not exist");
        }
        
        return new UserDetailsImp(user);
    }
    
}
