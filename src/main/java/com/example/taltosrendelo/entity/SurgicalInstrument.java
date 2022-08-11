package com.example.taltosrendelo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "surgical_instruments")
public class SurgicalInstrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @Column(name = "sale_price")
    private Integer salePrice;

    @Column(name = "is_yarn")
    private Boolean isYarn;

    @Column(name = "out_of_stock")
    private Integer outOfStock;

    @ManyToMany(mappedBy = "surgicals")
    private List<Treatment> treatments;

    
    public SurgicalInstrument() {
    }

    public SurgicalInstrument(Long id, String name, Integer quantity, Integer price, Integer salePrice, Boolean isYarn, Integer outOfStock) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.salePrice = salePrice;
        this.isYarn = isYarn;
        this.outOfStock = outOfStock;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public List<Treatment> getTreatments() {
        return this.treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public Boolean isIsYarn() {
        return this.isYarn;
    }

    public Boolean getIsYarn() {
        return this.isYarn;
    }

    public void setIsYarn(Boolean isYarn) {
        this.isYarn = isYarn;
    } 
    

    public Integer getOutOfStock() {
        return this.outOfStock;
    }

    public void setOutOfStock(Integer outOfStock) {
        this.outOfStock = outOfStock;
    }

}