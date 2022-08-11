package com.example.taltosrendelo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "treatment_id")
    private Long treatmentId;

    @Column(name = "material_name")
    private String materialName;

    @Column(name = "material_price")
    private Integer materialPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "gross_price")
    private Integer grossPrice;


    public Invoice() {
    }

    public Invoice(Long id, Long treatmentId, String materialName, Integer materialPrice, Integer quantity, Integer grossPrice) {
        this.id = id;
        this.treatmentId = treatmentId;
        this.materialName = materialName;
        this.materialPrice = materialPrice;
        this.quantity = quantity;
        this.grossPrice = grossPrice;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTreatmentId() {
        return this.treatmentId;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getMaterialName() {
        return this.materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getMaterialPrice() {
        return this.materialPrice;
    }

    public void setMaterialPrice(Integer materialPrice) {
        this.materialPrice = materialPrice;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getGrossPrice() {
        return this.grossPrice;
    }

    public void setGrossPrice(Integer grossPrice) {
        this.grossPrice = grossPrice;
    }
    
}
