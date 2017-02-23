package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Admin;
import com.example.model.Korisnik;

import com.example.service.AdminService;
import com.example.service.KorisnikService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/dodaj",method = RequestMethod.PUT)
	public ResponseEntity<Admin> dodajRestoran(@RequestBody Admin admin){
		
		List<Korisnik> sviKorisnici=korisnikService.getAllKorisnik();
		boolean okej=true;
		
		for(Korisnik k:sviKorisnici){
			if(k.getKorisnickoIme().equals(admin.getKorisnickoIme()) || k.getEmail().equals(admin.getEmail())){
				okej=false;
				break;
			}
		}
		
		if(okej){
			Admin registrovan=adminService.sacuvaj(admin);
			korisnikService.sacuvaj(admin);
			return new ResponseEntity<Admin>(registrovan,HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	
	}

}
