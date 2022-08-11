package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.taltosrendelo.entity.TreatmentType;
import com.example.taltosrendelo.repository.TreatmentTypeRepository;

@Service
public class TreatmentTypeSearchService {

    @Autowired
    private TreatmentTypeRepository treatmentTypeRepository;

    public List<TreatmentType> service(String name){
        List<TreatmentType> types;
        if(name == null || !StringUtils.hasLength(name)){
            types = treatmentTypeRepository.findAllByOrderByIdDesc();
        }else{
            types = treatmentTypeRepository.findByName(name);
        }
        return types;
    }
	
}