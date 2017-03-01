package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.PonudaPonudjac;


public interface PonudaPonudjacRepository extends JpaRepository<PonudaPonudjac, Long>{

	
	PonudaPonudjac findById(Long id);
	List<PonudaPonudjac> findAll();
	PonudaPonudjac save(PonudaPonudjac ponudaPonudjac);
	
}
