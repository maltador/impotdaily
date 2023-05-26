/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.controllers;

import com.kams.impotdaily.models.Company;
import com.kams.impotdaily.models.Impot;
import com.kams.impotdaily.services.CompanyService;
import com.kams.impotdaily.services.ImpotService;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author macbook
 */
@RestController
@RequestMapping("/api/impot")
public class ImpotController {
    
    @Autowired
    private ImpotService impotService;
    
    @GetMapping("")
    public ResponseEntity<List<Impot>> getAllImpots(HttpServletRequest request){
        long userId= (long) request.getAttribute("userId");
        List<Impot> impots= impotService.getAll();
        return new ResponseEntity<>(impots, HttpStatus.OK); 
    }
    
    @PostMapping("")
    public ResponseEntity<Impot> createImpot(HttpServletRequest request, @RequestBody Map<String, Object> companyMap) throws ParseException{
        long userId= (long) request.getAttribute("userId");
        String libelle= (String) companyMap.get("libelle");
        int type= (int) companyMap.get("type");
//        String datePaiement= (String) companyMap.get("datePaiement");
//        boolean editableDelay= (boolean) companyMap.get("editableDelay");
        Impot impot= impotService.create(libelle, type);
        return new ResponseEntity<>(impot, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Impot> editImpot(HttpServletRequest request, @PathVariable long id, @RequestBody Map<String, Object> impotMap) throws ParseException{
        long userId= (long) request.getAttribute("userId");
        String libelle= impotMap.containsKey("libelle") ?  (String) impotMap.get("libelle") : null ;
        int type= impotMap.containsKey("type") ? (int) impotMap.get("type") : 0;
        Impot impot= impotService.edit(id, libelle, type);
        return new ResponseEntity<>(impot, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Impot> getImpotById(HttpServletRequest request, @PathVariable long id){
        long userId= (long) request.getAttribute("userId");
        Impot impot= impotService.findImpotById(id);
        return new ResponseEntity<>(impot, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteImpot(HttpServletRequest request, @PathVariable long id){
        long userId= (long) request.getAttribute("userId");
        String message= impotService.deleteImpotById(id);
        Map<String, String> map= new HashMap<>();
        map.put("message", message);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
}
