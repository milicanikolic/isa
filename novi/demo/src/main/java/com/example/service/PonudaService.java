package com.example.service;

import java.util.List;

import com.example.model.Ponuda;

public interface PonudaService {

	
	Ponuda getPonuda(Long id);
	List<Ponuda> getAllPonuda();
	Ponuda sacuvaj(Ponuda ponuda);
	
	
}
