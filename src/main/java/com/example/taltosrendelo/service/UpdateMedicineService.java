package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Medicine;
import com.example.taltosrendelo.repository.MedicineRepository;

@Service
public class UpdateMedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    public void service(Medicine medicine){
        if(medicine.getPricePerKg() != null){
            medicine.setSalePricePerKg((int) Math.round(medicine.getPricePerKg() * 1.27 * 1.5));
        }
        if(medicine.getPricePerPackaging() != null){
            medicine.setSalePricePerPackaging((int) Math.round(medicine.getPricePerPackaging() * 1.27 * 1.5));
        }
        List<Medicine> medicines = medicineRepository.findAll();
        Boolean exists = false;
        for(Medicine it : medicines){
            if(it.getId() == medicine.getId()){
                exists = true;
                it.setName(medicine.getName());
                it.setPackaging(medicine.getPackaging());
                it.setIngredient(medicine.getIngredient());
                it.setPricePerKg(medicine.getPricePerKg());
                it.setSalePricePerKg(medicine.getSalePricePerKg());
                it.setPricePerPackaging(medicine.getPricePerPackaging());
                it.setSalePricePerPackaging(medicine.getSalePricePerPackaging());
                it.setQuantity(medicine.getQuantity());
                it.setState(medicine.getState());
                it.setOutOfStock(medicine.getOutOfStock());
                medicineRepository.save(it);
            }
        }
        if(!exists){
            medicineRepository.save(medicine);
        }
    }
	
}