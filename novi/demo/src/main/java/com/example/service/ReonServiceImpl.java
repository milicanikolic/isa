package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Reon;
import com.example.repository.ReonRepository;

@Service
public class ReonServiceImpl implements ReonService{
	
	@Autowired
	private ReonRepository reonRepository;

	@Override
	public Reon getReon(Long id) {
		// TODO Auto-generated method stub
		return reonRepository.findById(id);
	}

}
