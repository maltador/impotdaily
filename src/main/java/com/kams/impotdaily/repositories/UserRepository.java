/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kams.impotdaily.repositories;

import com.kams.impotdaily.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author macbook
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findAll();
//    List<User> findAllByCompany_id();
//    List<User> findAllByCompany_idAndRole();
    User save(User user);
    User findById(long id);
    User findByEmail(String email);
    User findByNameAndPostnameAndLastname(String name, String postname, String lastname);
    void deleteById(long id);
    long countByEmail(String email);
    
}
