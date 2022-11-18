/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.security;

import com.taoltech.finapp.services.UserDetailsServiceImp;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author user
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    UserDetailsServiceImp serviceImp;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        String token = this.getToken(request);
        
        if(token != null && this.jwtUtil.validateToken(token)) {
            
            String username = jwtUtil.getUsername(token);
            
            /**
             * Login 
             * 
             */
            UserDetails details = this.serviceImp.loadUserByUsername(username);
            
            /**
             * Auth
             * 
             */
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            /**
             * Update Context
             * 
             */
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        
        doFilter(request, response, filterChain);
    }
    
    private String getToken(HttpServletRequest request) {
        String token = null;
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
            token = header.substring("Bearer ".length());
        }
        
        return token;
    }
}
