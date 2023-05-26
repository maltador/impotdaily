/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.services;

import com.kams.impotdaily.exceptions.MyNotFoundException;
import com.kams.impotdaily.models.Impot;
import com.kams.impotdaily.repositories.ImpotRepository;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author macbook
 */
@Service
public class ImpotService {
    
    @Autowired
    private ImpotRepository impotRepository;
    
    public List<Impot> getAll(){
        return impotRepository.findAll();
    }
    
    public Impot create(String libelle, int type) throws ParseException{
       Impot impot= new Impot(libelle);
       impot.setType(type);
//       impot.setDatePaiement(AppUtils.getDateFrom(datePaiement, "dd/MM/yyyy"));
//       impot.setEditableDelay(editableDelay);
       return impotRepository.save(impot);
    }
    
    public Impot findImpotById(long id){
        Impot impot= impotRepository.findById(id);
        if (impot== null) {
            throw new MyNotFoundException("Impot non trouvé");
        }
        return impot;
    }
    
    public String deleteImpotById(long id){
        Impot impot= impotRepository.findById(id);
        if (impot == null) {
            throw new MyNotFoundException("L'impot n’existe pas");
        }
        impotRepository.deleteById(id);
        return "L'impot a été supprimée avec succès";
    }

    public Impot edit(long id, String name, int type) throws ParseException {
        Impot impot= new Impot();
        impot.setId(id);
        if (name != null) impot.setLibelle(name);
        if (type != 0) impot.setType(type);
        impot.setUpdatedAt(new Date());
//        if (datePaiement != null) impot.setDatePaiement(AppUtils.getDateFrom(datePaiement, "dd/MM/yyyy"));
//        if (editableDelay == true) impot.setEditableDelay(editableDelay);
        
        return impotRepository.save(impot);
    }
    
}
