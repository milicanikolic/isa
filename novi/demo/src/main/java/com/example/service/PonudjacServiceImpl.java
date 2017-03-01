package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Ponudjac;
import com.example.repository.PonudjacRepository;

@Service
public class PonudjacServiceImpl implements PonudjacService{

	@Autowired
	PonudjacRepository ponudjacRepository;
	
	@Override
	public Ponudjac getPonudjac(Long id) {
		
		return ponudjacRepository.findById(id);
	}

	@Override
	public List<Ponudjac> getAllPonudjac() {
		
		return ponudjacRepository.findAll();
	}

	@Override
	public Ponudjac sacuvaj(Ponudjac ponudjac) {
		Ponudjac pon=ponudjacRepository.save(ponudjac);
		return pon;
	}

}
