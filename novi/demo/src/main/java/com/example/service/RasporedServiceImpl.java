package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Raspored;
import com.example.repository.RasporedRepository;

@Service
public class RasporedServiceImpl implements RasporedService {

	@Autowired
	RasporedRepository rasporedRepository;
	
	@Override
	public Raspored sacuvaj(Raspored r) {
		// TODO Auto-generated method stub
		Raspored ras=rasporedRepository.save(r);
		return ras;
	}

	@Override
	public Raspored getRaspored(Long id) {
		// TODO Auto-generated method stub
		return rasporedRepository.findById(id);
	}

	@Override
	public List<Raspored> getAllRaspored() {
		// TODO Auto-generated method stub
		return rasporedRepository.findAll();
	}
	
}
