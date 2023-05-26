/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kams.impotdaily.repositories;

import com.kams.impotdaily.models.Impot;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author macbook
 */
@Repository
public interface ImpotRepository extends JpaRepository<Impot, Long>{
    List<Impot> findAll();
    Impot save(Impot impot);
    Impot findById(long id);
    void deleteById(long id);
}
