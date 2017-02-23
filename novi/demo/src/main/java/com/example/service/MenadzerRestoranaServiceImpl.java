package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.MenadzerRestorana;
import com.example.repository.MenadzerRestoranaRepository;

@Service
public class MenadzerRestoranaServiceImpl implements MenadzerRestoranaService{

	@Autowired
	private MenadzerRestoranaRepository menadzerRepository;
	
	@Override
	public MenadzerRestorana getAdmin(Long id) {
		
		return menadzerRepository.findById(id);
	}

	@Override
	public List<MenadzerRestorana> getAllMenadzeri() {
		
		return menadzerRepository.findAll();
	}

	@Override
	public MenadzerRestorana sacuvaj(MenadzerRestorana menadzer) {
		MenadzerRestorana m= menadzerRepository.save(menadzer);
		return m;
	}

}
