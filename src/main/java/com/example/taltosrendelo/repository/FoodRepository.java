package com.example.taltosrendelo.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.taltosrendelo.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM foods WHERE name LIKE %?1% ORDER BY id DESC", nativeQuery = true)
    List<Food> findByName(String name);

}