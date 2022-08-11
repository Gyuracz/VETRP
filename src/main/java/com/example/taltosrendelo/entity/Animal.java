package com.example.taltosrendelo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "chip")
    private Long chip;

    @Column(name = "species")
    private String species;

    @Column(name ="breed")
    private String breed;

    @Column(name = "gender")
    private String gender;

    @Column(name = "sex")
    private String sex;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "last_vaccination")
    private String lastVaccination;

    @Column(name = "last_vaccination_against_rabies")
    private String lastVaccinationAgainstRabies;

    @Column(name = "month_to_next_vaccination")
    private Integer monthToNextVaccination;

    @Column(name = "color")
    private String color;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "allergy")
    private String allergy;

    @Column(name = "passport")
    private String passport;

    @Column(name = "other")
    private String other;

    @ManyToOne(targetEntity = Owner.class)
    @JoinColumn(name = "owner_id")
    private Owner owner;


    public Animal() {
    }

    public Animal(Long id, String name, Long chip, String species, String breed, String gender, String sex, String birthday, String lastVaccination, String lastVaccinationAgainstRabies, Integer monthToNextVaccination, String color, Float weight, String allergy, String passport, String other, Owner owner) {
        this.id = id;
        this.name = name;
        this.chip = chip;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.sex = sex;
        this.birthday = birthday;
        this.lastVaccination = lastVaccination;
        this.lastVaccinationAgainstRabies = lastVaccinationAgainstRabies;
        this.monthToNextVaccination = monthToNextVaccination;
        this.color = color;
        this.weight = weight;
        this.allergy = allergy;
        this.passport = passport;
        this.other = other;
        this.owner = owner;
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

    public Long getChip() {
        return this.chip;
    }

    public void setChip(Long chip) {
        this.chip = chip;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLastVaccination() {
        return this.lastVaccination;
    }

    public void setLastVaccination(String lastVaccination) {
        this.lastVaccination = lastVaccination;
    }

    public String getLastVaccinationAgainstRabies() {
        return this.lastVaccinationAgainstRabies;
    }

    public void setLastVaccinationAgainstRabies(String lastVaccinationAgainstRabies) {
        this.lastVaccinationAgainstRabies = lastVaccinationAgainstRabies;
    }

    public Integer getMonthToNextVaccination() {
        return this.monthToNextVaccination;
    }

    public void setMonthToNextVaccination(Integer monthToNextVaccination) {
        this.monthToNextVaccination = monthToNextVaccination;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getWeight() {
        return this.weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getAllergy() {
        return this.allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getPassport() {
        return this.passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getOther() {
        return this.other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    
}
