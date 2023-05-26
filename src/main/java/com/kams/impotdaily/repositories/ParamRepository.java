/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kams.impotdaily.repositories;

import com.kams.impotdaily.models.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author macbook
 */
@Repository
public interface ParamRepository extends JpaRepository<Param, String>{
    Param getOne(String id);
    Param save(Param param);
       
}
