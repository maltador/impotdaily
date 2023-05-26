/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.services;

import com.kams.impotdaily.models.Param;
import com.kams.impotdaily.repositories.ParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author macbook
 */
@Service
public class ParamService {
    
    @Autowired
    private ParamRepository paramRepository;
    
    public long getCronValue(){
        return paramRepository.getOne("param").getInterval();
    }
    
    public Param saveParam(long interval){
        Param param= new Param("param", interval);
        return paramRepository.save(param);
    }
    
    public long getFixedDelay(){
        long cron= paramRepository.getOne("param").getInterval();
        return cron;
    }
    
    
}
