package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Pice;
import com.example.model.Ponudjac;


public interface PonudjacRepository extends JpaRepository<Ponudjac, Long>{
	
	Ponudjac findById(Long id);
	List<Ponudjac> findAll();
//	Ponudjac save(Ponudjac ponudjac);
	void delete(Ponudjac ponudjac);

}
