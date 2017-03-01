package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Namirnica;



public interface NamirnicaRepository extends JpaRepository<Namirnica, Long> {

	
	Namirnica findById(Long id);
	List<Namirnica> findAll();
	Namirnica save(Namirnica namirnica);
}
