package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Jelo;


public interface JeloRepository extends JpaRepository<Jelo, Long>{

	Jelo findById(Long id);
	List<Jelo> findAll();
}
