package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.TreatmentType;
import com.example.taltosrendelo.repository.TreatmentTypeRepository;

@Service
public class UpdateTreatmentTypeService {

    @Autowired
    private TreatmentTypeRepository treatmentTypeRepository;

    public void service(TreatmentType treatmentType){
        List<TreatmentType> treatmentTypes = treatmentTypeRepository.findAll();
        Boolean exists = false;
        for(TreatmentType it : treatmentTypes){
            if(it.getId() == treatmentType.getId()){
                exists = true;
                it.setName(treatmentType.getName());
                it.setPrice(treatmentType.getPrice());
                treatmentTypeRepository.save(it);
            }
        }
        if(!exists){
            treatmentTypeRepository.save(treatmentType);
        }
    }
	
}