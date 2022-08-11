package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Food;
import com.example.taltosrendelo.repository.FoodRepository;

@Service
public class UpdateFoodService {

    @Autowired
    private FoodRepository foodRepository;

    public void service(Food food){
        if(food.getPricePerKg() != null){
            food.setSalePricePerKg((int) Math.round(food.getPricePerKg() * 1.27 * 1.5));
        }
        if(food.getPricePerPackaging() != null){
            food.setSalePricePerPackaging((int) Math.round(food.getPricePerPackaging() * 1.27 * 1.5));
        }
        List<Food> foods = foodRepository.findAll();
        Boolean exists = false;
        for(Food it : foods){
            if(it.getId() == food.getId()){
                exists = true;
                it.setName(food.getName());
                it.setPackaging(food.getPackaging());
                it.setPricePerKg(food.getPricePerKg());
                it.setSalePricePerKg(food.getSalePricePerKg());
                it.setPricePerPackaging(food.getPricePerPackaging());
                it.setSalePricePerPackaging(food.getSalePricePerPackaging());
                it.setQuantity(food.getQuantity());
                it.setOutOfStock(food.getOutOfStock());
                foodRepository.save(it);
            }
        }
        if(!exists){
            foodRepository.save(food);
        }
    }
	
}