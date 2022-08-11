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
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "packaging")
    private String packaging;
    
    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "price_per_kg")
    private Integer pricePerKg;

    @Column(name = "sale_price_per_kg")
    private Integer salePricePerKg;

    @Column(name = "price_per_packaging")
    private Integer pricePerPackaging;

    @Column(name = "sale_price_per_packaging")
    private Integer salePricePerPackaging;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "state")
    private String state;

    @Column(name = "out_of_stock")
    private Integer outOfStock;

    @ManyToMany(mappedBy = "medicines")
    private List<Treatment> treatments;


    public Medicine() {
    }

    public Medicine(Long id, String name, String packaging, String ingredient, Integer pricePerKg, Integer salePricePerKg, Integer pricePerPackaging, Integer salePricePerPackaging, Integer quantity, String state, Integer outOfStock) {
        this.id = id;
        this.name = name;
        this.packaging = packaging;
        this.ingredient = ingredient;
        this.pricePerKg = pricePerKg;
        this.salePricePerKg = salePricePerKg;
        this.pricePerPackaging = pricePerPackaging;
        this.salePricePerPackaging = salePricePerPackaging;
        this.quantity = quantity;
        this.state = state;
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

    public String getPackaging() {
        return this.packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getIngredient() {
        return this.ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getPricePerKg() {
        return this.pricePerKg;
    }

    public void setPricePerKg(Integer pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public Integer getPricePerPackaging() {
        return this.pricePerPackaging;
    }

    public void setPricePerPackaging(Integer pricePerPackaging) {
        this.pricePerPackaging = pricePerPackaging;
    }

    public Integer getSalePricePerKg() {
        return this.salePricePerKg;
    }

    public void setSalePricePerKg(Integer salePricePerKg) {
        this.salePricePerKg = salePricePerKg;
    }

    public Integer getSalePricePerPackaging() {
        return this.salePricePerPackaging;
    }

    public void setSalePricePerPackaging(Integer salePricePerPackaging) {
        this.salePricePerPackaging = salePricePerPackaging;
    }

    public List<Treatment> getTreatments() {
        return this.treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }    

    public Integer getOutOfStock() {
        return this.outOfStock;
    }

    public void setOutOfStock(Integer outOfStock) {
        this.outOfStock = outOfStock;
    }
	
}