package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Prijateljstva;

public interface PrijateljstvaRepository extends JpaRepository<Prijateljstva, Long>{

	Prijateljstva findById(Long id);
	List<Prijateljstva> findAll();
	Prijateljstva save(Prijateljstva prijateljstvo);
	
}
