package com.example.service;

import java.util.List;

import com.example.model.Korisnik;
import com.example.model.Sto;

public interface StoService {
	Sto sacuvaj(Sto sto);
	Sto getSto(Long id); 
	List<Sto> findAll();

}
