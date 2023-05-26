/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kams.impotdaily.repositories;

import com.kams.impotdaily.models.Company;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author macbook
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    List<Company> findAll();
    Company save(Company company);
    Company findById(long id);
    void deleteById(long id);
}
