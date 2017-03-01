package com.example.service;

import java.util.List;

import com.example.model.Radnik;

public interface RadnikService {
	
	List<Radnik> getAllRadnik();
	Radnik getRadnik(Long id);
	Radnik save(Radnik r);

}
