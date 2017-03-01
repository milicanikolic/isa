package com.example.service;

import java.util.List;

import com.example.model.Ponudjac;

public interface PonudjacService {

	Ponudjac getPonudjac(Long id);
	List<Ponudjac> getAllPonudjac();
	Ponudjac sacuvaj(Ponudjac ponudjac);
	
	
}
