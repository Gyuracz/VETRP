package com.example.taltosrendelo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.SurgicalInstrument;
import com.example.taltosrendelo.repository.SurgicalInstrumentRepository;

@Service
public class MultiplySurgicalPriceService {
	
    @Autowired
    private SurgicalInstrumentRepository surgicalInstrumentRepository;

    public void service(Float multiplier){
        List<SurgicalInstrument> surgicals = surgicalInstrumentRepository.findAll();
        List<SurgicalInstrument> newSurgicals = new ArrayList<>();
        for(SurgicalInstrument surgical : surgicals){
            surgical.setPrice((int) Math.round(surgical.getPrice() * multiplier));
            surgical.setSalePrice((int) Math.round(surgical.getSalePrice() * multiplier));
            newSurgicals.add(surgical);
        }
        surgicalInstrumentRepository.saveAll(newSurgicals);
    }

}