package com.example.taltosrendelo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Food;
import com.example.taltosrendelo.repository.FoodRepository;

@Service
public class MultiplyFoodPriceService {

    @Autowired
    private FoodRepository foodRepository;

    public void service(Float multiplier){
        List<Food> foods = foodRepository.findAll();
        List<Food> newFoods = new ArrayList<>();
        for(Food food : foods){
            food.setPricePerKg((int) Math.round(food.getPricePerKg() * multiplier));
            food.setSalePricePerKg((int) Math.round(food.getSalePricePerKg() * multiplier));
            food.setPricePerPackaging((int) Math.round(food.getPricePerPackaging() * multiplier));
            food.setSalePricePerPackaging((int) Math.round(food.getSalePricePerPackaging() * multiplier));
            newFoods.add(food);
        }
        foodRepository.saveAll(newFoods);
    }
	
}