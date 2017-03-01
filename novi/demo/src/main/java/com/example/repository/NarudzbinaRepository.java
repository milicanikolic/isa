package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.GostNarudzbina;

public interface NarudzbinaRepository extends JpaRepository<GostNarudzbina, Long> {
	public List<GostNarudzbina> findAll();
	public GostNarudzbina findById(Long id);

	

}
