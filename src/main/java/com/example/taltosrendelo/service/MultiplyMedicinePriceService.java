package com.example.taltosrendelo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Medicine;
import com.example.taltosrendelo.repository.MedicineRepository;

@Service
public class MultiplyMedicinePriceService {

    @Autowired
    private MedicineRepository medicineRepository;

    public void service(Float multiplier){
        List<Medicine> medicines = medicineRepository.findAll();
        List<Medicine> newMedicines = new ArrayList<>();
        for(Medicine medicine : medicines){
            medicine.setPricePerKg((int) Math.round(medicine.getPricePerKg() * multiplier));
            medicine.setSalePricePerKg((int) Math.round(medicine.getSalePricePerKg() * multiplier));
            medicine.setPricePerPackaging((int) Math.round(medicine.getPricePerPackaging() * multiplier));
            medicine.setSalePricePerPackaging((int) Math.round(medicine.getSalePricePerPackaging() * multiplier));
            newMedicines.add(medicine);
        }
        medicineRepository.saveAll(newMedicines);
    }
	
}