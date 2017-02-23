package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Jelo;
import com.example.repository.JeloRepository;

@Service
public class JeloServiceImpl implements JeloService{

	@Autowired
	private JeloRepository jeloRepository;
	
	@Override
	public Jelo getJelo(Long id) {
		
		return jeloRepository.findById(id);
	}

	@Override
	public List<Jelo> getAllJelo() {
		
		return jeloRepository.findAll();
	}

	@Override
	public Jelo sacuvaj(Jelo jelo) {
		Jelo j= jeloRepository.save(jelo);
		return j;
	}

}
