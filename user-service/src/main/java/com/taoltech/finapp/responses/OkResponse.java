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
@EqualsAndHashCode(callSuper = false)
public class OkResponse extends BaseResponse {
    
    private Object data;

    public OkResponse(String message, Object data) {
        super(true, message);
        this.data = data;
    }
}
