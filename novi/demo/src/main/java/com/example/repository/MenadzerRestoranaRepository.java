package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.MenadzerRestorana;


public interface MenadzerRestoranaRepository extends JpaRepository<MenadzerRestorana, Long>{
	
	MenadzerRestorana findById(Long id);
	List<MenadzerRestorana> findAll();
	MenadzerRestorana findByKorisnickoIme(String korisnickoIme);

}
