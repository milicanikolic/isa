package com.example.service;

import java.util.List;

import com.example.model.Prijateljstva;

public interface PrijateljstvaService {

	Prijateljstva getPrijateljstvo(Long id);
	List<Prijateljstva> getAllPrijateljstva();
	Prijateljstva sacuvaj(Prijateljstva prijateljstvo);
	
}
