package com.example.controller;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.enumeracije.StatusRezervacije;
import com.example.enumeracije.StatusSto;
import com.example.model.MenadzerRestorana;
import com.example.model.Reon;
import com.example.model.Restoran;
import com.example.model.Rezervacija;
import com.example.model.Sto;
import com.example.service.ReonService;
import com.example.service.StoService;

@Controller
@RequestMapping("/restoranMenadzer")
public class RestoranMenadzerController {

	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private ReonService reonService;
	 @Autowired
	 private StoService stoService;
	 
	 @RequestMapping(value="/dodajSto/{id}",method = RequestMethod.POST)
	 public ResponseEntity<Sto> dodajSto(@PathVariable Long idReona, @RequestBody Sto sto, @RequestBody MenadzerRestorana mr){
		 boolean vecIma=false;
		
		 Reon reon=reonService.getReon(idReona);
		 Restoran res=mr.getRestoran();
		 Set<Sto> stolovi=res.getStoloviURestoranu();
		 Set<Sto> stoloviUTomReonu=new HashSet<>();
		 for(Sto jedan:stolovi) {
			 if(jedan.getTipReona().equals(reon.getTipReona())) {
				 stoloviUTomReonu.add(jedan);
			 }
		 }
		 
		 for(Sto uReonu:stoloviUTomReonu) {
			 if(uReonu.getI()==sto.getI() && uReonu.getJ()==sto.getJ()) {
				 vecIma=true;
				 break;
			 }
		 }
			 
		if(!vecIma) {
			sto.setStatus(StatusSto.SLOBODAN);
			Sto dodat=stoService.sacuvaj(sto);
			return new ResponseEntity<Sto>(dodat, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		 
	 }
	 
	 
	 @RequestMapping(value="/obrisiSto/{id}",method = RequestMethod.POST)
	 public ResponseEntity<Sto> obrisiSto(@PathVariable Long idSto){
		 boolean obrisi=true;
		 Sto stoZaBrisanje=stoService.getSto(idSto);
		 for(Rezervacija r: stoZaBrisanje.getRezervacijeStola()) {
			 if(r.getStatusRez().equals(StatusRezervacije.AKTIVNA)) {
				 obrisi=false;
				 break;
			 }
		 }
		 
		 if(obrisi) {
			 stoZaBrisanje.setStatus(StatusSto.NERASPOREDJEN);
			 stoZaBrisanje.setId(stoZaBrisanje.getId());
			 Sto obrisan=stoService.sacuvaj(stoZaBrisanje);
			 return new ResponseEntity<Sto>(obrisan, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 }
	 }
	 
	 
	
}
