/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.controllers;

import com.kams.impotdaily.models.Param;
import com.kams.impotdaily.services.ParamService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author macbook
 */
@RestController
@RequestMapping("/api/param")
public class ParamController {
    
    @Autowired
    private ParamService paramService;
    
    @PostMapping("")
    public ResponseEntity<Param> saveParam(HttpServletRequest request, @RequestBody Map<String, Integer> paramMap){
        long userId= (long) request.getAttribute("userId");
        long interval= (long) paramMap.get("interval");
        Param param= paramService.saveParam(interval);
        return new ResponseEntity<>(param, HttpStatus.OK);
    }
    
    @GetMapping("/cron")
    public ResponseEntity<Long> setCron(HttpServletRequest request){
        long userId= (long) request.getAttribute("userId");
        long resp= paramService.getCronValue();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
}
