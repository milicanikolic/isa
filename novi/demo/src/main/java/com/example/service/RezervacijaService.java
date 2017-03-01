package com.example.service;

import com.example.model.Rezervacija;

public interface RezervacijaService {
	
	Rezervacija sacuvaj(Rezervacija rez);
	Rezervacija getRezervacija(Long id); 
}
