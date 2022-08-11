package com.example.taltosrendelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.taltosrendelo.entity.Treatment;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    @Query(value = "SELECT * FROM treatments t JOIN treatment_types tt ON tt.id = t.treatment_type_id JOIN animals a ON a.id = t.animal_id", nativeQuery = true)
    List<Treatment> findAllByOrderByIdDesc();

    @Query(value = "SELECT animal_id FROM treatments WHERE id = ?1", nativeQuery = true)
    Long findAnimalIdById(Long id);

    @Query(value = "SELECT * FROM treatments WHERE animal_id = ?1 ORDER BY id DESC", nativeQuery = true)
    List<Treatment> findAllByAnimalId(Long id);
	
}