package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Rezervacija;

import java.util.List;


public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
	Rezervacija findById(Long id);
	List<Rezervacija> findAll();
}
