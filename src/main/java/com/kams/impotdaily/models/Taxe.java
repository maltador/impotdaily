/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author macbook
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "taxes")
public class Taxe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "impot_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Impot impot;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company company;
    private long money;
    private double montant;
    @Column(name = "type_penality")
    private int typePenality= 1;
    private double penalities;
    private boolean valid= true;
    @Column(name = "date_paiement")
    private Date datePaiement= new Date();
    @Column(name = "editable_delay")
    private boolean editableDelay= false;
    @Column(name = "created_at")
    private Date createdAt= new Date();
    @Column(name = "updated_at")
    private Date updatedAt= new Date();
    
    
}
