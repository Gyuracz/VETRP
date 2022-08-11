package com.example.taltosrendelo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.taltosrendelo.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByTreatmentId(Long id);

    @Query(value = "SELECT * FROM invoices WHERE treatment_id = ?1 AND material_name = ?2", nativeQuery = true)
    Optional<Invoice> findByTreatmentIdAndMaterialName(Long id, String name);
    
}
