package com.example.taltosrendelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.taltosrendelo.entity.TreatmentType;

@Repository
public interface TreatmentTypeRepository extends JpaRepository<TreatmentType, Long> {

    List<TreatmentType> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM treatment_types WHERE name LIKE %?1% ORDER BY id DESC", nativeQuery = true)
    List<TreatmentType> findByName(String name);

}