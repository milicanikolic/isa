package com.example.service;

import java.util.List;

import com.example.model.Jelo;

public interface JeloService {

	Jelo getJelo(Long id);
	List<Jelo> getAllJelo();
	Jelo sacuvaj(Jelo jelo);
}
