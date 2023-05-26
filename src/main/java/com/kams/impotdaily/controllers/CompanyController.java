/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.controllers;

import com.kams.impotdaily.models.Company;
import com.kams.impotdaily.services.CompanyService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/api/company")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;
    
    @GetMapping("")
    public ResponseEntity<List<Company>> getAllCompanies(/*HttpServletRequest request*/){
        //long userId= (long) request.getAttribute("userId");
        List<Company> companies= companyService.getAll();
        return new ResponseEntity<>(companies, HttpStatus.OK); 
    }
    
    @PostMapping("")
    public ResponseEntity<Company> createCompany(@RequestBody Map<String, String> companyMap){
        String name= (String) companyMap.get("name");
        Company company= companyService.create(name);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Company> editCompany(@PathVariable long id, @RequestBody Map<String, String> companyMap){
        String name= (String) companyMap.get("name");
        Company company= companyService.edit(id, name);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable long id){
        Company company= companyService.findCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCompany(@PathVariable long id){
        String message= companyService.deleteCompanyById(id);
        Map<String, String> map= new HashMap<>();
        map.put("message", message);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
}
