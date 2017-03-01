package com.example.service;

import java.util.List;

import com.example.model.PonudaPonudjac;

public interface PonudaPonudjacService {
	PonudaPonudjac getPonudaPonudjac(Long id);
	List<PonudaPonudjac> getAllPonudaPonudjac();
	PonudaPonudjac sacuvaj(PonudaPonudjac ponudaPonudjac);

}
