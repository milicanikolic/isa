package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Admin;
import com.example.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Admin getAdmin(Long id) {
		
		return adminRepository.findById(id);
	}

	@Override
	public List<Admin> getAllAdmin() {
		
		return adminRepository.findAll();
	}

	@Override
	public Admin sacuvaj(Admin admin) {
		Admin a=adminRepository.save(admin);
		return a;
	}

	@Override
	public Admin getAdminByKorisnickoIme(String korisnickoIme) {
		
		return adminRepository.findByKorisnickoIme(korisnickoIme);
	}

}
