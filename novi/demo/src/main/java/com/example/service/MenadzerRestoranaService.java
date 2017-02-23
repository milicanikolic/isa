package com.example.service;

import java.util.List;

import com.example.model.MenadzerRestorana;



public interface MenadzerRestoranaService {

	MenadzerRestorana getAdmin(Long id);
	List<MenadzerRestorana> getAllMenadzeri();
	MenadzerRestorana sacuvaj(MenadzerRestorana menadzer);
}
