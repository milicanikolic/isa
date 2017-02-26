package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.model.Pice;



public interface PiceRepository extends JpaRepository<Pice, Long>{

	Pice findById(Long id);
	List<Pice> findAll();
	Pice save(Pice pice);
	void delete(Pice pice);
	
}
