package com.example.isef01projekt.service;

import com.example.isef01projekt.entity.Mitarbeiter;

import java.util.List;

public interface MitarbeiterService {

    List<Mitarbeiter> findAll();
    
    List<Mitarbeiter> findByRolle();     
    
    List<Mitarbeiter> findByProjekt(int projektid);
    
    List<Mitarbeiter> findByCount();

}
