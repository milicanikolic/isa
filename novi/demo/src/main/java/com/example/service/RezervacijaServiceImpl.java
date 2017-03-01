package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Rezervacija;
import com.example.repository.RezervacijaRepository;

@Service
public class RezervacijaServiceImpl implements RezervacijaService{

	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Override
	public Rezervacija sacuvaj(Rezervacija rez) {
		Rezervacija rezervacija= rezervacijaRepository.save(rez);
		return rezervacija;
	}

	@Override
	public Rezervacija getRezervacija(Long id) {
		// TODO Auto-generated method stub
		return rezervacijaRepository.findById(id);
	}

}
