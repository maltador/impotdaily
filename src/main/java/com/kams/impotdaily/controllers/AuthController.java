/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.controllers;

import com.kams.impotdaily.Constants;
import com.kams.impotdaily.models.User;
import com.kams.impotdaily.services.SendMailService;
import com.kams.impotdaily.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author macbook
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private SendMailService sendMailService;
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> loginMap){
        String email= loginMap.get("email");
        String password= loginMap.get("password");
        User user= userService.loginUser(email, password);
        String token= generateJWTToken(user);
        Map<String, Object> map= new HashMap<>();
        map.put("user", user);
        map.put("token", token);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody Map<String, Object> signUpMap){
        String email= (String) signUpMap.get("email");
        String nom= (String) signUpMap.get("name");
        String postname= (String) signUpMap.get("postname");
        String lastname= (String) signUpMap.get("lastname");
        String password= (String) signUpMap.get("password");
        String role= (String) signUpMap.get("role");
        int companyIdIn= (int) signUpMap.get("companyId");
        long companyId= Long.valueOf(companyIdIn);
        //long companyId= (long) signUpMap.get("companyId");
        User user= userService.registerUser(email, nom, postname, lastname, role, password, companyId);
        Map<String, Object> map= new HashMap<>();
        map.put("message", "Enregistrement reussi, Veuillez-vous connecter");
        sendMailService.sendSimpleMail(user.getEmail(), 
                "Bonjour Mr(Mme) "+user.getLastname()+" "+user.getName()+",\n Votre compte IMPOTDAILY a été créé avec succès.",
                "Confirmation IMPOTDAILY");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    private String generateJWTToken(User user){
        long timestamp= System.currentTimeMillis();
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp+ Constants.TOKEN_VALIDITY))
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("postname", user.getPostname())
                .claim("lastname", user.getLastname())
                .claim("role", user.getRole())
                .compact();
                
    }
    
}
