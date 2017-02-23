package com.example.service;

import java.util.List;

import com.example.model.Restoran;

public interface RestoranService {

	List<Restoran> getAllRestoran();
	Restoran getRestoran(Long id);
	Restoran sacuvaj(Restoran res);
	void delete(Restoran r);
	
}
