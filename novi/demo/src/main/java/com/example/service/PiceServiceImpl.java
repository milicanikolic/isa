package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Pice;
import com.example.repository.PiceRepository;

@Service
public class PiceServiceImpl implements PiceService{
	
	@Autowired
	private PiceRepository piceRepository;

	@Override
	public Pice getPice(Long id) {
		
		return piceRepository.findById(id);
	}

	@Override
	public List<Pice> getAllPice() {
		
		return piceRepository.findAll();
	}

	@Override
	public Pice sacuvaj(Pice pice) {
		Pice p=piceRepository.save(pice);
		return p;
	}

	
	
	
}
