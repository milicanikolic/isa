package com.example.service;

import java.util.List;

import com.example.model.Gost;

public interface GostService {
	 
	List<Gost> getAllGost();
	Gost getGost(Long id);
	
}
