package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.taltosrendelo.entity.Medicine;
import com.example.taltosrendelo.repository.MedicineRepository;

@Service
public class MedicineSearchService {

    @Autowired
    private MedicineRepository medicineRepository;

    public List<Medicine> service(String name){
        List<Medicine> medicines;
        if(name == null || !StringUtils.hasLength(name)){
            medicines = medicineRepository.findAllByOrderByIdDesc();
        }else{
            medicines = medicineRepository.findByName(name);
        }
        return medicines;
    }
	
}