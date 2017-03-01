package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Korisnik;
import com.example.model.Sto;

public interface StoRepository extends JpaRepository<Sto, Long>{
	Sto save(Sto sto);
	Sto findById(Long id);
	List<Sto> findAll();
}
