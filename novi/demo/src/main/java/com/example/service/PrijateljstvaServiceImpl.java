package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Prijateljstva;
import com.example.repository.PrijateljstvaRepository;

@Service
public class PrijateljstvaServiceImpl implements PrijateljstvaService{

	@Autowired
	PrijateljstvaRepository prijateljstvaRepository;
	
	@Override
	public Prijateljstva getPrijateljstvo(Long id) {
		
		return prijateljstvaRepository.findById(id);
	}

	@Override
	public List<Prijateljstva> getAllPrijateljstva() {
		
		return prijateljstvaRepository.findAll();
	}

	@Override
	public Prijateljstva sacuvaj(Prijateljstva prijateljstvo) {
		Prijateljstva p= prijateljstvaRepository.save(prijateljstvo);
		return p;
	}

}
