/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.services;

import com.taoltech.finapp.models.User;
import com.taoltech.finapp.models.Account;
import com.taoltech.finapp.repositories.AccountRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class AccountService {
    
    @Autowired
    AccountRepository repository;
    
    public void generate(User user) {
        System.out.println("Called");
        while (true) {            
            String number = RandomStringUtils.randomNumeric(10);
            boolean accountExist = this.repository.existsByNumber(number);
            if(!accountExist) {
                Account account = new Account(number, user);
                this.repository.save(account);
                break;
            }
        }
    }
}
