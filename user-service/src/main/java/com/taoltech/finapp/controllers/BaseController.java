/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.controllers;

import com.taoltech.finapp.models.User;
import com.taoltech.finapp.responses.ErrResponse;
import com.taoltech.finapp.responses.OkResponse;
import com.taoltech.finapp.services.UserDetailsImp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author user
 */
public class BaseController {
    
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp details = (UserDetailsImp) authentication.getPrincipal();
        return details.getUser();
    }
    
    public ResponseEntity<?> okResponse(String message, Object data) {
        return ResponseEntity.ok().body(new OkResponse(message, data));
    }
    
    public ResponseEntity<?> errResponse(String message, Object error) {
        return ResponseEntity.ok().body(new ErrResponse(message, error));
    }
}
