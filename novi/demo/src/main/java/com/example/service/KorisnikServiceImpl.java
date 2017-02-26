package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Korisnik;
import com.example.repository.KorisnikRepository;

@Service
public class KorisnikServiceImpl implements KorisnikService{

	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Override
	public Korisnik getKorisnik(Long id) {
		
		return korisnikRepository.findById(id);
	}

	@Override
	public List<Korisnik> getAllKorisnik() {
		
		return korisnikRepository.findAll();
	}

	@Override
	public Korisnik sacuvaj(Korisnik korisnik) {
		Korisnik k=korisnikRepository.save(korisnik);
		return k;
	}

	@Override
	public Korisnik getKorisnikByKorisnickoIme(String korisnickoIme) {
		
		return korisnikRepository.findByKorisnickoIme(korisnickoIme);
	}

}
