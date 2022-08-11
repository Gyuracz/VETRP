package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Owner;
import com.example.taltosrendelo.repository.OwnerRepository;

@Service
public class UpdateOwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public void service(Owner owner){
        List<Owner> owners = ownerRepository.findAll();
        Boolean exists = false;
        for(Owner it : owners){
            if(it.getId() == owner.getId()){
                exists = true;
                it.setName(owner.getName());
                it.setAddress(owner.getAddress());
                it.setPhone(owner.getPhone());
                it.setEmail(owner.getEmail());
                it.setOther(owner.getOther());
                it.setAnimals(owner.getAnimals());
                ownerRepository.save(it);
            }
        }
        if(!exists){
            ownerRepository.save(owner);
        }
    }
	
}