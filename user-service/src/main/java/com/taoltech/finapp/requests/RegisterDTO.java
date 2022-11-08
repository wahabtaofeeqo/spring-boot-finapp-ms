/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.requests;

import com.taoltech.finapp.models.User;
import lombok.Data;

/**
 *
 * @author user
 */
@Data
public class RegisterDTO {
    
    private String name;
    private String email;
    private String phone;
    private String password;
    
    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        return user;
    }
}
