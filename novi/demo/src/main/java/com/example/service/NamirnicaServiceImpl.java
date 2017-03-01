package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Namirnica;
import com.example.repository.NamirnicaRepository;

@Service
public class NamirnicaServiceImpl implements NamirnicaService{

	
	@Autowired
	private NamirnicaRepository namirnicaRepository;
	
	@Override
	public Namirnica getNamirnica(Long id) {
		
		return namirnicaRepository.findById(id);
	}

	@Override
	public List<Namirnica> getAllNamirnica() {
		
		return namirnicaRepository.findAll();
	}

	@Override
	public Namirnica sacuvaj(Namirnica namirnica) {
		Namirnica n=namirnicaRepository.save(namirnica);
		return n;
	}

	@Override
	public void izbrisi(Namirnica namirnica) {
		namirnicaRepository.delete(namirnica);
		
	}

}
