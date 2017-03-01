package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Ponuda;
import com.example.repository.PonudaRepository;

@Service
public class PonudaServiceImpl implements PonudaService{

	@Autowired
	private PonudaRepository ponudaRepository;
	
	@Override
	public Ponuda getPonuda(Long id) {
		
		return ponudaRepository.findById(id);
	}

	@Override
	public List<Ponuda> getAllPonuda() {
		
		return ponudaRepository.findAll();
	}

	@Override
	public Ponuda sacuvaj(Ponuda ponuda) {
		Ponuda pon= ponudaRepository.save(ponuda);
		return pon;
	}

}
