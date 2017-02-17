package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Gost;
import com.example.repository.GostRepository;

@Service
public class GostServiceImpl implements GostService{
	
	@Autowired
	private GostRepository gostRepository;

	@Override
	public Gost getGost(Long id) {
		
		return gostRepository.findById(id);
	}

	@Override
	public List<Gost> getAllGost() {
		
		return gostRepository.findAll();
	}

	@Override
	public Gost save(Gost id) {
		
		return gostRepository.save(id);
	}
	
	@Override
	 public Gost findByKorisnickoIme(String korisnickoIme) {
	  Gost g= gostRepository.findByKorisnickoIme(korisnickoIme);
	 return g;
	 }

	@Override
	public Gost updateGost(Gost gost,Long id) {
		gost.setId(id);
		Gost g= gostRepository.save(gost);
	return g;
	}
	
}
