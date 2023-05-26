/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.services;

import com.kams.impotdaily.exceptions.MyNotFoundException;
import com.kams.impotdaily.models.Company;
import com.kams.impotdaily.repositories.CompanyRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author macbook
 */
@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;
    
    public List<Company> getAll(){
        return companyRepository.findAll();
    }
    
    public Company edit(long id, String name){
       Company company= companyRepository.findById(id);
       if (company== null) {
            throw new MyNotFoundException("Société non trouvée");
        }
       company.setName(name);
       company.setUpdatedAt(new Date());
       return companyRepository.save(company);
    }
    
    public Company create(String name){
       Company company= new Company(name);
       return companyRepository.save(company);
    }
    
    public Company findCompanyById(long id){
        Company company= companyRepository.findById(id);
        if (company== null) {
            throw new MyNotFoundException("Société non trouvée");
        }
        return company;
    }
    
    public String deleteCompanyById(long id){
        Company company= companyRepository.findById(id);
        if (company == null) {
            throw new MyNotFoundException("La société n’existe pas");
        }
        companyRepository.deleteById(id);
        return "La société a été supprimée avec succès";
    }
}
