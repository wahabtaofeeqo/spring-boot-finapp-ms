/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.security;

import com.taoltech.finapp.services.UserDetailsImp;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */
@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;
    
    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(generateKey())
                .build().parseClaimsJws(token).getBody().getSubject();
    }
    
    public String generateToken(Authentication authentication) {
        
        Map<String, Object> claims = new HashMap<>();
        UserDetailsImp details = (UserDetailsImp) authentication.getPrincipal();
        
        return Jwts.builder()
                .setSubject(details.getUsername())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .signWith(generateKey()).compact();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(generateKey())
                .build().parseClaimsJws(token);
            return true;
        } 
        catch (Exception e) {
            System.out.println("Token Error: " + e.getMessage());
        }
        return false;
    }
                
    private Key generateKey() {
        byte[] encodedKey = Base64.getDecoder().decode(secret);
        Key key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA512");
        return key;
    }
}
