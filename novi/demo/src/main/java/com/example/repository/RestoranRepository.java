package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Restoran;


public interface RestoranRepository extends JpaRepository<Restoran, Long>{
	
	Restoran findById(Long id);

}
