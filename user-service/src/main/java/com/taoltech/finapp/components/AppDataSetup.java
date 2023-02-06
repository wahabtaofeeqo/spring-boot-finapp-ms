/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.components;

import com.taoltech.finapp.models.Role;
import com.taoltech.finapp.models.User;
import com.taoltech.finapp.repositories.RoleRepository;
import com.taoltech.finapp.repositories.UserRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */
@Component
public class AppDataSetup implements ApplicationListener<ContextRefreshedEvent>{

    private boolean alreadySetup;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(alreadySetup) return;
        
        // Create Roles
        createRoleIfNotExist("ROLE_USER");
        createRoleIfNotExist("ROLE_ADMIN");
        
        // Create Admin
        createAdminIfNotExist();
        
        //
        alreadySetup = true;
    }
    
    private void createRoleIfNotExist(String name) {
        Role role = roleRepository.findByName(name);
        if(role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
    }
    
    private void createAdminIfNotExist() {
        
        Role role = roleRepository.findByName("ROLE_ADMIN");
        User admin = userRepository.findByEmail("admin@yahoo.com");
        if(admin == null) {
            admin = new User();
            admin.setName("Admin Tao");
            admin.setPhone("09087654321");
            admin.setEmail("admin@yahoo.com");
            admin.setRoles(Arrays.asList(role));
            admin.setPassword(passwordEncoder.encode("admin123"));

            //
            userRepository.save(admin);
        }
    }
}
