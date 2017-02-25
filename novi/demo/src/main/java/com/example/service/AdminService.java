package com.example.service;

import java.util.List;

import com.example.model.Admin;

public interface AdminService {
	
	Admin getAdmin(Long id);
	List<Admin> getAllAdmin();
	Admin sacuvaj(Admin admin);
	Admin getAdminByKorisnickoIme(String korisnickoIme);

}
