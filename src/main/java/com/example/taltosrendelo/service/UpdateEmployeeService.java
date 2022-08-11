package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Employee;
import com.example.taltosrendelo.repository.EmployeeRepository;

@Service
public class UpdateEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void service(Employee employee){
        List<Employee> employees = employeeRepository.findAll();
        Boolean exists = false;
        for(Employee it : employees){
            if(it.getId() == employee.getId()){
                exists = true;
                it.setName(employee.getName());
                it.setPhone(employee.getPhone());
                it.setPost(employee.getPost());
                it.setEmail(employee.getEmail());
                employeeRepository.save(it);
            }
        }
        if(!exists){
            employeeRepository.save(employee);
        }
    }
	
}