package com.example.service;

import java.util.List;

import com.example.model.Rezervacija;

public interface RezervacijaService {
	
	Rezervacija sacuvaj(Rezervacija rez);
	Rezervacija getRezervacija(Long id); 
	List<Rezervacija> getAllRezervacije();
}
