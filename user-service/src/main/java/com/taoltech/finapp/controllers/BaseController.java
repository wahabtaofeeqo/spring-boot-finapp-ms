/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.controllers;

import com.taoltech.finapp.responses.OkResponse;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author user
 */
public class BaseController {
    
    public ResponseEntity<?> okResponse(String message, Object data) {
        return ResponseEntity.ok().body(new OkResponse(message, data));
    }
}
