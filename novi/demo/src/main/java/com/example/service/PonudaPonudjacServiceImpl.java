package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.PonudaPonudjac;
import com.example.repository.PonudaPonudjacRepository;

@Service
public class PonudaPonudjacServiceImpl implements PonudaPonudjacService{

	@Autowired
	PonudaPonudjacRepository ponudaPonudjacRepository;
	
	@Override
	public PonudaPonudjac getPonudaPonudjac(Long id) {
		
		return ponudaPonudjacRepository.findById(id);
	}

	@Override
	public List<PonudaPonudjac> getAllPonudaPonudjac() {
		
		return ponudaPonudjacRepository.findAll();
	}

	@Override
	public PonudaPonudjac sacuvaj(PonudaPonudjac ponudaPonudjac) {
		PonudaPonudjac pp=ponudaPonudjacRepository.save(ponudaPonudjac);
		return pp;
	}

}
