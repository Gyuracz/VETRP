package com.example.taltosrendelo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.taltosrendelo.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findAllByOrderByIdDesc();

    @Query(value = "SELECT name FROM owners ORDER BY id DESC", nativeQuery = true)
    List<String> getAllName();

    @Query(value = "SELECT * FROM owners WHERE id = ?1", nativeQuery = true)
    Owner findOwnerById(Long id);

    @Query(value = "SELECT * FROM owners WHERE name LIKE %?1% ORDER BY id DESC", nativeQuery = true)
    List<Owner> findByName(String param);

    @Query(value = "SELECT * FROM owners WHERE address LIKE %?1% ORDER BY id DESC", nativeQuery = true)
    List<Owner> findByAddress(String param);

    @Query(value = "SELECT * FROM owners WHERE phone LIKE %?1% ORDER BY id DESC", nativeQuery = true)
    List<Owner> findByPhone(String param);

    @Query(value = "SELECT * FROM owners WHERE email LIKE %?1% ORDER BY id DESC", nativeQuery = true)
    List<Owner> findByEmail(String param);

    @Query(value = "SELECT * FROM owners WHERE other LIKE %?1% ORDER BY id DESC", nativeQuery = true)
    List<Owner> findByOther(String param);

}
