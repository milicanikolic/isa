package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Radnik;


public interface RadnikRepository extends JpaRepository<Radnik, Long>{

	public List<Radnik> findAll();
	public Radnik findById(Long id);
	
}
