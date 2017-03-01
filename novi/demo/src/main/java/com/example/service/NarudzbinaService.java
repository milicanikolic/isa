package com.example.service;

import java.util.List;

import com.example.model.GostNarudzbina;

public interface NarudzbinaService {
	List<GostNarudzbina> getAllNarudzbina();
	GostNarudzbina getNarudzbina(Long id);
	GostNarudzbina save(GostNarudzbina id);
	
}
