package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.MenadzerRestorana;
import com.example.model.Ponuda;



public interface PonudaRepository extends JpaRepository<Ponuda, Long>{
	
	Ponuda findById(Long id);
	List<Ponuda> findAll();
	Ponuda save(Ponuda ponuda);
	Ponuda findByMenadzerRestorana(MenadzerRestorana menadzerRestorana);

}
