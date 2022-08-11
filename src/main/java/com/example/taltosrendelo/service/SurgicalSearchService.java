package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.taltosrendelo.entity.SurgicalInstrument;
import com.example.taltosrendelo.repository.SurgicalInstrumentRepository;

@Service
public class SurgicalSearchService {

    @Autowired
    private SurgicalInstrumentRepository surgicalInstrumentRepository;

    public List<SurgicalInstrument> service(String name){
        List<SurgicalInstrument> surgicals;
        if(name == null || !StringUtils.hasLength(name)){
            surgicals = surgicalInstrumentRepository.findAllByOrderByIdDesc();
        }else{
            surgicals = surgicalInstrumentRepository.findByName(name);
        }
        return surgicals;
    }
	
}