package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Sto;
import com.example.repository.StoRepository;

@Service
public class StoServiceImpl implements StoService{
	@Autowired
	StoRepository stoRepository;

	@Override
	public Sto sacuvaj(Sto sto) {
		Sto s=stoRepository.save(sto);
		return s;
	}

	@Override
	public Sto getSto(Long id) {
		// TODO Auto-generated method stub
		return stoRepository.findById(id);
	}

	@Override
	public List<Sto> findAll() {
		
		return stoRepository.findAll();
	}

}
