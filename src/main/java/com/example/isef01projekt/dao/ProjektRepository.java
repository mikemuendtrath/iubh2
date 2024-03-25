package com.example.isef01projekt.dao;

import com.example.isef01projekt.entity.Projekt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjektRepository extends JpaRepository<Projekt, Integer> {
	
	@Query("select p from Projekt p where p.projektleiter = ?1")
	List<Projekt> findByName(String projektleiter);
}
