package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Radnik;
import com.example.repository.RadnikRepository;

@Service
public class RadnikServiceImpl implements RadnikService{

	@Autowired
	private RadnikRepository radnikRepository;
	
	@Override
	public List<Radnik> getAllRadnik() {
		
		return radnikRepository.findAll();
	}

	@Override
	public Radnik getRadnik(Long id) {
		
		return radnikRepository.findById(id);
	}

	@Override
	public Radnik save(Radnik r) {
		Radnik radnik= radnikRepository.save(r);
		return radnik;
	}

	
}
