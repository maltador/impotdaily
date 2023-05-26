/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kams.impotdaily.repositories;

import com.kams.impotdaily.models.Taxe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author macbook
 */
@Repository
public interface TaxeRepository extends JpaRepository<Taxe, Long>{
    List<Taxe> findAll();
    Taxe save(Taxe taxe);
    Taxe findById(long id);
    void deleteById(long id);
    List<Taxe> findByValidTrue();
    List<Taxe> findAllByCompany_id(long company_id);
    List<Taxe> findAllByCompany_idAndValidTrue(long company_id);
    
}
