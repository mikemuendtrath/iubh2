package com.example.isef01projekt.service;

import com.example.isef01projekt.dao.MitarbeiterRepository;
import com.example.isef01projekt.entity.Mitarbeiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MitarbeiterServiceImp implements MitarbeiterService{

    private MitarbeiterRepository mitarbeiterRepository;

    @Autowired
    public  MitarbeiterServiceImp(MitarbeiterRepository mitarbeiterRepository){
    this.mitarbeiterRepository = mitarbeiterRepository;
    }

    @Override
    public List<Mitarbeiter> findAll() {
        return mitarbeiterRepository.findAll();
    }
    
    public List<Mitarbeiter> findByRolle() {
    	return mitarbeiterRepository.findByRolle("Projektleiter");
    }    
    
    public List<Mitarbeiter> findByProjekt(int projektid) {
    	return mitarbeiterRepository.findByProjekt(projektid);
    }
    
    public List<Mitarbeiter> findByCount() {    	
    	return mitarbeiterRepository.findByCount("Mitarbeiter");
    }

}
