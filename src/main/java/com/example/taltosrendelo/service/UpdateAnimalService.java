package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Animal;
import com.example.taltosrendelo.repository.AnimalRepository;

@Service
public class UpdateAnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public void service(Animal animal){
        List<Animal> animals = animalRepository.findAll();
        Boolean exists = false;
        for(Animal it : animals){
            if(it.getId() == animal.getId()){
                exists = true;
                it.setName(animal.getName());
                it.setChip(animal.getChip());
                it.setBreed(animal.getBreed());
                it.setAllergy(animal.getAllergy());
                it.setBirthday(animal.getBirthday());
                it.setColor(animal.getColor());
                it.setGender(animal.getGender());
                it.setLastVaccination(animal.getLastVaccination());
                it.setLastVaccinationAgainstRabies(animal.getLastVaccinationAgainstRabies());
                it.setMonthToNextVaccination(animal.getMonthToNextVaccination());
                it.setOther(animal.getOther());
                it.setPassport(animal.getPassport());
                it.setSex(animal.getSex());
                it.setSpecies(animal.getSpecies());
                it.setWeight(animal.getWeight());
                it.setOwner(animal.getOwner());
                animalRepository.save(it);
            }
        }
        if(!exists){
            animalRepository.save(animal);
        }
    }
	
}