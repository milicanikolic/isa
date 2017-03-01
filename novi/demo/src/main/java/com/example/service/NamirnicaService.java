package com.example.service;

import java.util.List;

import com.example.model.Namirnica;

public interface NamirnicaService {

	Namirnica getNamirnica(Long id);
	List<Namirnica> getAllNamirnica();
	Namirnica sacuvaj(Namirnica namirnica);
    void izbrisi(Namirnica namirnica);
	
}
