package com.example.service;

import java.util.List;

import com.example.model.Pice;

public interface PiceService {

	
	Pice getPice(Long id);
	List<Pice> getAllPice();
	Pice sacuvaj(Pice pice);
	
}
