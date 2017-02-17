package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Gost;


public interface GostService {
	 
	List<Gost> getAllGost();
	Gost getGost(Long id);
	Gost save(Gost id);
	Gost findByKorisnickoIme(String korisnickoIme);
	Gost updateGost(Gost g,Long id);
}
