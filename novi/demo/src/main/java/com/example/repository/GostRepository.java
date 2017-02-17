package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Gost;

public interface GostRepository extends JpaRepository<Gost, Long>{
	
	public List<Gost> findAll();
	public Gost findById(Long id);
//	public Gost update(Gost gost,Long id);
//	public Gost save(Gost id);
	public Gost findByKorisnickoIme(String korisnickoIme);
	
}