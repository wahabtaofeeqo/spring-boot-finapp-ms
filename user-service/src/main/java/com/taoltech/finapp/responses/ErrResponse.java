/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author user
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ErrResponse extends BaseResponse {
    
    private Object error;

    public ErrResponse(String message) {
        super(false, message);
    }
    
    public ErrResponse(String message, Object error) {
        super(false, message);
        this.error = error;
    }
    
}
