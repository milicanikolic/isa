package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Restoran;
import com.example.repository.RestoranRepository;
@Service
public class RestoranServiceImpl implements RestoranService{

	@Autowired
	private RestoranRepository restoranRepository;
	
	@Override
	public List<Restoran> getAllRestoran() {
		
		return restoranRepository.findAll();
	}

	@Override
	public Restoran getRestoran(Long id) {
		
		return restoranRepository.findById(id);
	}

	@Override
	public Restoran sacuvaj(Restoran res) {
		Restoran r= restoranRepository.save(res);
	return r;
	}


	@Override
	public void delete(Restoran r) {
		restoranRepository.delete(r);
		
	}

}
