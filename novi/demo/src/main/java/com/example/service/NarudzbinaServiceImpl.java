package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.GostNarudzbina;
import com.example.repository.NarudzbinaRepository;

@Service
public class NarudzbinaServiceImpl implements NarudzbinaService {

	@Autowired
	private NarudzbinaRepository narudzbinaRepository;

	@Override
	public List<GostNarudzbina> getAllNarudzbina() {
		// TODO Auto-generated method stub

		return narudzbinaRepository.findAll();
	}

	@Override
	public GostNarudzbina getNarudzbina(Long id) {
		// TODO Auto-generated method stub
		return narudzbinaRepository.findById(id);
	}

	@Override
	public GostNarudzbina save(GostNarudzbina id) {
		// TODO Auto-generated method stub
		return narudzbinaRepository.save(id);
	}
}
