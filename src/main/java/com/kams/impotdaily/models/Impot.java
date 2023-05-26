/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author macbook
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "impots")
public class Impot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String libelle;
    private int type;
    @Column(name = "created_at")
    private Date createdAt= new Date();
    @Column(name = "updated_at")
    private Date updatedAt= new Date();
    
    public Impot(String libelle){
        this.libelle= libelle;
    }
    
}
