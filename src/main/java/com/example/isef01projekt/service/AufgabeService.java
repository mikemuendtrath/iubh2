package com.example.isef01projekt.service;

import com.example.isef01projekt.entity.Aufgabe;

import java.util.List;

public interface AufgabeService {

    List<Aufgabe> findAll();

    Aufgabe findById(int theId);

    Aufgabe save(Aufgabe aufgabe);

    void deleteById(int theId);

}
