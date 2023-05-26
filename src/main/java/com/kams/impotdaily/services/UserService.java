/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.services;

import com.kams.impotdaily.exceptions.MyBadRequestException;
import com.kams.impotdaily.models.Company;
import com.kams.impotdaily.models.User;
import com.kams.impotdaily.repositories.CompanyRepository;
import com.kams.impotdaily.repositories.UserRepository;
import com.kams.impotdaily.utils.Regexs;
import java.util.Locale;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author macbook
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    
    
    public User loginUser(String email, String password){
        User user= userRepository.findByEmail(email);
        if (user == null) {
            throw new MyBadRequestException("L'utilisateur "+email+" n'a aucun compte.");
        }
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new MyBadRequestException("Vous avez saisis un mauvais mot de passe");
        }
       return user;
    }
    
    public User registerUser(String email, String nom, String postname, String lastname, String role, String password, long companyId){
        System.out.println("{"
                + "\n nom: "+nom+","
                + "\n postname: "+postname+","
                + "\n lastname: "+lastname+","
                + "\n password: "+password+","
                + "\n role: "+role+","
                + "\n companyId: "+companyId+"\n}");
        String hashedPassword= BCrypt.hashpw(password, BCrypt.gensalt(10));
        if (email != null) {
            email= email.toLowerCase(Locale.FRENCH);
        }
        if (!email.matches(Regexs.REGEX_MAIL)) {
            throw new MyBadRequestException("L'adresse e-mail renseignée n’est pas valide");
        }
        Company company= companyRepository.findById(companyId);
        if (company == null) {
            throw new MyBadRequestException("La société renseignée n’existe pas");
        }
        System.out.println("La societe est :"+company.getName());
        long count= userRepository.countByEmail(email);
        if (count > 0) {
            throw new MyBadRequestException("L'adresse e-mail renseignée est déjà utilisée ");
        }
        
        User user= new User();
        user.setEmail(email);
        user.setName(nom);
        user.setPostname(postname);
        user.setLastname(lastname);
        user.setRole(role == null ? "user" : role);
        user.setPassword(hashedPassword);
        user.setCompany(company);
        return userRepository.save(user);
    }
    
}
