package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Gost;
import com.example.repository.GostRepository;

@Service
public class GostServiceImpl implements GostService{
	
	@Autowired
	private GostRepository gostRepository;

	@Override
	public Gost getGost(Long id) {
		
		return gostRepository.findById(id);
	}

	@Override
	public List<Gost> getAllGost() {
		
		return gostRepository.findAll();
	}
	
	
}
