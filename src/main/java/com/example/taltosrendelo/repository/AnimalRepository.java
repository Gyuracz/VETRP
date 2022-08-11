package com.example.taltosrendelo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.taltosrendelo.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllByOrderByIdDesc();
    
    @Query(value = "SELECT * FROM animals WHERE owner_id = ?1 ORDER BY id DESC", nativeQuery = true)
    List<Animal> findAllByOwnerId(Long id);

    @Query(value = "SELECT owner_id FROM animals WHERE id = ?1", nativeQuery = true)
    Optional<Long> findOwnerIdById(Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM animals WHERE id = ?1", nativeQuery = true)
    void deleteAnimalById(Long id);

}
