package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Korisnik;




public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
	
	Korisnik findById(Long id);
	List<Korisnik> findAll();
	Korisnik save(Korisnik pice);

}
