package com.example.service;

import java.util.List;

import com.example.model.Raspored;

public interface RasporedService {
	Raspored sacuvaj(Raspored sto);
	Raspored getRaspored(Long id); 
	List<Raspored> getAllRaspored();
}
