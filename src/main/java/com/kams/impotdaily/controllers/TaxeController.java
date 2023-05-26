/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.controllers;

import com.kams.impotdaily.models.Taxe;
import com.kams.impotdaily.services.TaxeService;
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
@RequestMapping("/api/taxe")
public class TaxeController {
    
    @Autowired
    private TaxeService taxeService;
    
    @PostMapping("")
    public ResponseEntity<Taxe> subscribeTaxe(HttpServletRequest request, @RequestBody Map<String, Object> taxeMap) throws ParseException{
        long userId= (long) request.getAttribute("userId");
        int impotId= (int) taxeMap.get("impotId");
        String datePaiement= (String) taxeMap.get("datePaiement");
        boolean editableDelay= (boolean) taxeMap.get("editableDelay");
        int money= (int) taxeMap.get("money");
        double montant= (double) taxeMap.get("montant");
        int typePenality= (int) taxeMap.get("typePenality");
        double penalites= (double) taxeMap.get("penalities");
        Taxe taxe= taxeService.subscribeTaxe(userId, Long.valueOf(impotId), datePaiement, editableDelay, money, montant,
                typePenality, penalites);
        return new ResponseEntity<>(taxe, HttpStatus.OK);
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Taxe> editTaxe(HttpServletRequest request, @PathVariable int id, @RequestBody Map<String, Object> taxeMap) throws ParseException{
        long userId= (long) request.getAttribute("userId");
        int impotId= (int) taxeMap.get("impotId");
        boolean editableDelay= (boolean) taxeMap.get("editableDelay");
        String datePaiement= taxeMap.containsKey("datePaiement") ? (String) taxeMap.get("datePaiement") : null;
        int money= taxeMap.containsKey("money") ?  (int) taxeMap.get("money") : 0;
        double montant= taxeMap.containsKey("montant") ? (double) taxeMap.get("montant") : 0.0;
        int typePenality= taxeMap.containsKey("typePenality") ? (int) taxeMap.get("typePenality") : 0;
        double penalites= taxeMap.containsKey("penalities") ? (double) taxeMap.get("penalities") : 0.0;
        Taxe taxe= taxeService.editTaxe(userId, id,  datePaiement, editableDelay, money, montant, typePenality, penalites);
        return new ResponseEntity<>(taxe, HttpStatus.OK);
        
    }
    
    @GetMapping("")
    public ResponseEntity<List<Taxe>> getAllTaxesOfUser(HttpServletRequest request){
        long userId= (long) request.getAttribute("userId");
        List<Taxe> listTaxe= taxeService.getAllTaxesOfCompany(userId);
        return new ResponseEntity<>(listTaxe, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTaxe(HttpServletRequest request, @PathVariable int id){
        long userId= (long) request.getAttribute("userId");
        String reponse= taxeService.deteTaxe(id);
        Map<String, String> taxeMap= new HashMap<>();
        taxeMap.put("message", reponse);
        return new ResponseEntity<>(taxeMap, HttpStatus.OK);
    }
    
    @GetMapping("invalid/{id}")
    public ResponseEntity<Map<String, String>> invalidTaxe(HttpServletRequest request, @PathVariable int id){
        long userId= (long) request.getAttribute("userId");
        Taxe taxe= taxeService.invalidTaxe(id);
        Map<String, String> taxeMap= new HashMap<>();
        taxeMap.put("message", "La taxe "+taxe.getImpot().getLibelle()+"  a été invalidée avec succès ");
        return new ResponseEntity<>(taxeMap, HttpStatus.OK);
    }
    
}
