package com.example.isef01projekt.service;

import com.example.isef01projekt.entity.Projekt;

import java.util.List;

public interface ProjektService {

    List<Projekt> findAll();

    Projekt findById(int theId);

    Projekt save(Projekt projekt);

    void deleteById(int theId);

}
