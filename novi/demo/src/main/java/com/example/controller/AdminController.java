package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.enumeracije.VrsteKorisnika;
import com.example.model.Admin;
import com.example.model.Korisnik;

import com.example.service.AdminService;
import com.example.service.KorisnikService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/dodaj",method = RequestMethod.POST)
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
			admin.setVrstaKorisnika(VrsteKorisnika.ADMIN);
			Admin registrovan=adminService.sacuvaj(admin);
			korisnikService.sacuvaj(admin);
			return new ResponseEntity<Admin>(registrovan,HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	
	}
	

	
	
	@RequestMapping(value="/uzmi/{korisnickoIme}",method = RequestMethod.GET)
	public ResponseEntity<Admin> uzmiAdminaPoKorisnickomImenu(@PathVariable String korisnickoIme){
logger.info("uzao uzmi admin back    "+korisnickoIme);
		Admin admin= adminService.getAdminByKorisnickoIme(korisnickoIme);
		logger.info(" SE ODUSEVIO: "+admin.getEmail());
		return new ResponseEntity<Admin>(admin,HttpStatus.CREATED);
	}

}
