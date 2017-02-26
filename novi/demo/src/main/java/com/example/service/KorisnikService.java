package com.example.service;

import java.util.List;

import com.example.model.Korisnik;



public interface KorisnikService {

	Korisnik getKorisnik(Long id);
	List<Korisnik> getAllKorisnik();
	Korisnik sacuvaj(Korisnik korisnik);
	Korisnik getKorisnikByKorisnickoIme(String korisnickoIme);
	
}
