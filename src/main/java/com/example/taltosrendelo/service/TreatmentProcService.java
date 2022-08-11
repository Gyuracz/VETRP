package com.example.taltosrendelo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Animal;
import com.example.taltosrendelo.entity.Treatment;
import com.example.taltosrendelo.repository.TreatmentRepository;

@Service
public class TreatmentProcService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    public void service(Animal animal, Treatment treatment){
        List<Treatment> treatments = treatmentRepository.findAll();
        Boolean exists = false;
        for(Treatment it : treatments){
            if(it.getId() == treatment.getId()){
                exists = true;

                it.setAnimal(animal);
                it.setDoctor(treatment.getDoctor());
                it.setHistoryField(treatment.getHistoryField());
                it.setPresentField(treatment.getPresentField());
                it.setTreatmentField(treatment.getTreatmentField());
                treatmentRepository.save(it);
            }
        }

        if(!exists){
            treatment.setAnimal(animal);
            treatment.setDate(new Date());
            treatmentRepository.save(treatment);
        }
    }
	
}