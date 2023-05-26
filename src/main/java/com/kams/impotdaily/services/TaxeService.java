/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.services;

import com.kams.impotdaily.exceptions.MyBadRequestException;
import com.kams.impotdaily.models.Impot;
import com.kams.impotdaily.models.Company;
import com.kams.impotdaily.models.Taxe;
import com.kams.impotdaily.models.User;
import com.kams.impotdaily.repositories.ImpotRepository;
import com.kams.impotdaily.repositories.TaxeRepository;
import com.kams.impotdaily.repositories.UserRepository;
import com.kams.impotdaily.utils.AppUtils;
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
public class TaxeService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImpotRepository impotRepository;
    @Autowired
    private TaxeRepository taxeRepository;
    
    public Taxe subscribeTaxe(long userId, long impotId, String datePaiement, boolean editableDelay, 
            int money, double montant, int typePenality,
            double penalites) throws ParseException{
        Taxe taxe= new Taxe();
        User user= userRepository.findById(userId);
        if (user == null) throw new MyBadRequestException("Utilisateur inconnue");
        Company company= user.getCompany();
        Impot impot= impotRepository.findById(impotId);
        if (impot == null) throw new MyBadRequestException("Impot inconnue");
        taxe.setDatePaiement(AppUtils.getDateFrom(datePaiement, "dd/MM/yyyy"));
        taxe.setEditableDelay(editableDelay);
        taxe.setCompany(company);
        taxe.setImpot(impot);
        taxe.setMoney(money);
        taxe.setMontant(montant);
        taxe.setTypePenality(typePenality);
        taxe.setPenalities(penalites);
        taxe.setValid(true);
        return taxeRepository.save(taxe);   
    }
    
    public Taxe editTaxe(long userId, long taxeId, String datePaiement, boolean editableDelay, int money, double montant, int typePenality, 
            double penalites) throws ParseException {
        Taxe taxe= taxeRepository.findById(taxeId);
        Impot impot= taxe.getImpot();
        if (taxe == null) throw new MyBadRequestException("Taxe inconnue");
        if (datePaiement != null) taxe.setDatePaiement(AppUtils.getDateFrom(datePaiement, "dd/MM/yyyy"));
        if (taxe.isEditableDelay() != editableDelay)  taxe.setEditableDelay(editableDelay);
        if (money != 0) taxe.setMoney(money);
        if (montant != 0.0) taxe.setMontant(montant);
        if (typePenality != 0) taxe.setTypePenality(typePenality);
        if (penalites != 0.0) taxe.setPenalities(penalites);
        impot.setUpdatedAt(new Date());
        return taxeRepository.save(taxe);
        
    }
    
    public List<Taxe> getAllTaxesOfCompany(long userId){
        User user= userRepository.findById(userId);
        return taxeRepository.findAllByCompany_id(user.getCompany().getId());
    }
    
    public String deteTaxe(int id){
        taxeRepository.deleteById(Long.valueOf(id));
        return "Taxe supprimée avec succès";
    }
    
    public Taxe invalidTaxe(int id){
        //Taxe taxe= taxeRepository.findById(Long.valueOf(id));
        long idLong= Long.valueOf(id);
        Taxe taxe= taxeRepository.findById(idLong);
        if (taxe == null) throw new MyBadRequestException("Taxe inconnue");
        taxe.setValid(false);
        return taxeRepository.save(taxe);
    }
}
