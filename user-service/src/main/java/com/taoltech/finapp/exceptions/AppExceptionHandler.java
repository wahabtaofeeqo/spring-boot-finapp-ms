/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.exceptions;

import com.taoltech.finapp.responses.ErrResponse;
import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author user
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<?> handleAccessDeniedException(Exception ex, WebRequest request) {
        ErrResponse response = new ErrResponse("Access denied");
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception ex, WebRequest request) {
        ErrResponse response = new ErrResponse(ex.getMessage(), ex.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
    }
}
