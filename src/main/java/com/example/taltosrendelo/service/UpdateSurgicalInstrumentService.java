package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.SurgicalInstrument;
import com.example.taltosrendelo.repository.SurgicalInstrumentRepository;

@Service
public class UpdateSurgicalInstrumentService {

    @Autowired
    private SurgicalInstrumentRepository surgicalInstrumentRepository;

    public void service(SurgicalInstrument surgicalInstrument){
        if(surgicalInstrument.getPrice() != null){
            surgicalInstrument.setSalePrice((int) Math.round(surgicalInstrument.getPrice() * 1.27 * 1.5));
        }
        List<SurgicalInstrument> surgicals = surgicalInstrumentRepository.findAll();
        Boolean exists = false;
        for(SurgicalInstrument it : surgicals){
            if(it.getId() == surgicalInstrument.getId()){
                exists = true;
                it.setName(surgicalInstrument.getName());
                it.setPrice(surgicalInstrument.getPrice());
                it.setSalePrice(surgicalInstrument.getSalePrice());
                it.setIsYarn(surgicalInstrument.getIsYarn());
                it.setQuantity(surgicalInstrument.getQuantity());
                it.setOutOfStock(surgicalInstrument.getOutOfStock());
                surgicalInstrumentRepository.save(it);
            }
        }
        if(!exists){
            surgicalInstrumentRepository.save(surgicalInstrument);
        }
    }
	
}