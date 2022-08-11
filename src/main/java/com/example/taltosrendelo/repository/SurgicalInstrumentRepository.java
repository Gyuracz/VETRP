package com.example.taltosrendelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.taltosrendelo.entity.SurgicalInstrument;

@Repository
public interface SurgicalInstrumentRepository extends JpaRepository<SurgicalInstrument, Long> {

    List<SurgicalInstrument> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM surgical_instruments WHERE name LIKE %?1% ORDER BY id DESC", nativeQuery = true)
    List<SurgicalInstrument> findByName(String name);
	
}