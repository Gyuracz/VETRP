package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.taltosrendelo.entity.Food;
import com.example.taltosrendelo.repository.FoodRepository;

@Service
public class FoodSearchService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> service(String name){
        List<Food> foods;
        if(name == null || !StringUtils.hasLength(name)){
            foods = foodRepository.findAllByOrderByIdDesc();
        }else{
            foods = foodRepository.findByName(name);
        }
        return foods;
    }
	
}