package com.example.taltosrendelo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TreatmentType.class)
    @JoinColumn(name = "treatment_type_id")
    private TreatmentType treatmentType;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Animal.class)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Column(name = "history", columnDefinition = "LONGTEXT")
    private String historyField;

    @Column(name = "present", columnDefinition = "LONGTEXT")
    private String presentField;

    @Column(name = "treatment", columnDefinition = "LONGTEXT")
    private String treatmentField;

    @Column(name = "doctor")
    private String doctor;

    @ManyToMany
    @JoinTable(
        name = "rel_treatment_medicine",
        joinColumns = @JoinColumn(name = "treatment_id"),
        inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicines;

    @ManyToMany
    @JoinTable(
        name = "rel_treatment_surgical",
        joinColumns = @JoinColumn(name = "treatment_id"),
        inverseJoinColumns = @JoinColumn(name = "surgical_id")
    )
    private List<SurgicalInstrument> surgicals;

    @Column(name = "date")
    private Date date;


    public Treatment() {
    }

    public Treatment(Long id, TreatmentType treatmentType, Animal animal, String historyField, String presentField, String treatmentField, String doctor) {
        this.id = id;
        this.treatmentType = treatmentType;
        this.animal = animal;
        this.historyField = historyField;
        this.presentField = presentField;
        this.treatmentField = treatmentField;
        this.doctor = doctor;
        this.date = new Date();
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TreatmentType getTreatmentType() {
        return this.treatmentType;
    }

    public void setTreatmentType(TreatmentType treatmentType) {
        this.treatmentType = treatmentType;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public List<Medicine> getMedicines() {
        return this.medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public List<SurgicalInstrument> getSurgicals() {
        return this.surgicals;
    }

    public void setSurgicals(List<SurgicalInstrument> surgicals) {
        this.surgicals = surgicals;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHistoryField() {
        return this.historyField;
    }

    public void setHistoryField(String historyField) {
        this.historyField = historyField;
    }

    public String getPresentField() {
        return this.presentField;
    }

    public void setPresentField(String presentField) {
        this.presentField = presentField;
    }

    public String getTreatmentField() {
        return this.treatmentField;
    }

    public void setTreatmentField(String treatmentField) {
        this.treatmentField = treatmentField;
    }

    public String getDoctor() {
        return this.doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
	
}