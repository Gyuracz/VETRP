package com.example.taltosrendelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.taltosrendelo.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM medicines WHERE name LIKE %?1% ORDER BY id DESC", nativeQuery = true)
    List<Medicine> findByName(String name);

}