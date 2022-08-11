package com.example.taltosrendelo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Food;
import com.example.taltosrendelo.entity.Medicine;
import com.example.taltosrendelo.entity.SurgicalInstrument;
import com.example.taltosrendelo.repository.FoodRepository;
import com.example.taltosrendelo.repository.MedicineRepository;
import com.example.taltosrendelo.repository.SurgicalInstrumentRepository;

@Service
public class InventoryService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private SurgicalInstrumentRepository surgicalInstrumentRepository;

    public Map<String, Integer> getLowQuantityOfFoods(){
        Map<String, Integer> lows = new HashMap<String, Integer>();
        for(Food it : foodRepository.findAll()){
            if(it.getQuantity() != null && it.getOutOfStock() != null){
                if(it.getQuantity() <= it.getOutOfStock()){
                    lows.put(it.getName(), it.getQuantity());
                }
            }
        }
        return lows;
    }

    public Map<String, Integer> getLowQuantityOfMedicines(){
        Map<String, Integer> lows = new HashMap<String, Integer>();
        for(Medicine it : medicineRepository.findAll()){
            if(it.getQuantity() != null && it.getOutOfStock() != null){
                if(it.getQuantity() <= it.getOutOfStock()){
                    lows.put(it.getName(), it.getQuantity());
                }
            }
        }
        return lows;
    }

    public Map<String, Integer> getLowQuantityOfSurgicals(){
        Map<String, Integer> lows = new HashMap<String, Integer>();
        for(SurgicalInstrument it : surgicalInstrumentRepository.findAll()){
            if(it.getQuantity() != null && it.getOutOfStock() != null){
                if(it.getQuantity() <= it.getOutOfStock()){
                    lows.put(it.getName(), it.getQuantity());
                }
            }
        }
        return lows;
    }
	
}