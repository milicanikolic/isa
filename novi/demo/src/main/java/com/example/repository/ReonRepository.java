package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Reon;

public interface ReonRepository extends JpaRepository<Reon, Long>{
	Reon findById(Long id);
}
