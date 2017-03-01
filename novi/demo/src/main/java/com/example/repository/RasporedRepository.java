package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Raspored;
import com.example.model.Sto;

public interface RasporedRepository extends JpaRepository<Raspored, Long> {
	Raspored save(Sto sto);
	Raspored findById(Long id);
	List<Raspored> findAll();
}
