package com.example.taltosrendelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.taltosrendelo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM employees WHERE post = 'Doktor'", nativeQuery = true)
    List<Employee> findAllDoctor();
	
}