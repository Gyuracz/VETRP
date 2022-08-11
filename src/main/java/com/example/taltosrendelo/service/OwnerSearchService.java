package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.taltosrendelo.entity.Owner;
import com.example.taltosrendelo.repository.OwnerRepository;

@Service
public class OwnerSearchService {
	
    @Autowired
    private OwnerRepository ownerRepository;

    public List<Owner> service(String param, String condition){
        List<Owner> owners;
        if(param == null || !StringUtils.hasLength(param)){
            owners = ownerRepository.findAllByOrderByIdDesc();
        }else{
            switch(condition){
                case "name":
                    owners = ownerRepository.findByName(param);
                    break;
                case "address":
                    owners = ownerRepository.findByAddress(param);
                    break;
                case "phone":
                    owners = ownerRepository.findByPhone(param);
                    break;
                case "email":
                    owners = ownerRepository.findByEmail(param);
                    break;
                case "other":
                    owners = ownerRepository.findByOther(param);
                    break;
                default:
                    owners = ownerRepository.findAllByOrderByIdDesc();
                    break;
            }
        }
        return owners;
    }

}