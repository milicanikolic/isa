package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Gost;

public interface GostRepository extends JpaRepository<Gost, Long>{
	
	public Gost findById(Long id);
	
}