/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
public class IndexController extends BaseController {
    
    @GetMapping("/")
    public ResponseEntity<?> index() {
        System.out.println("Called");
        return this.okResponse("Welcome 🥂", null);
    }
}
