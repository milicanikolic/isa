package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.enumeracije.Smena;
import com.example.enumeracije.StatusRezervacije;
import com.example.enumeracije.StatusSto;
import com.example.model.MenadzerRestorana;
import com.example.model.Radnik;
import com.example.model.Raspored;
import com.example.model.Reon;
import com.example.model.Restoran;
import com.example.model.Rezervacija;
import com.example.model.Sto;
import com.example.service.MenadzerRestoranaService;
import com.example.service.RadnikService;
import com.example.service.RasporedService;
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
	 
	 @Autowired
	 private RasporedService rasporedService;
	 @Autowired
	 private MenadzerRestoranaService menadzerService;
	 @Autowired
	 private RadnikService radnikService;
	 
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
	 @RequestMapping(value="/dodajSmenu/{idMen}/{idRadnika}/{smena}/{datum}/{idReona}",method = RequestMethod.POST)
	  public ResponseEntity<List<Radnik>> dodajSmenu(@PathVariable Long idMen, @PathVariable Long idRadnika, @PathVariable String smena, @PathVariable String datum, @PathVariable Long idReona){
	   
	   Raspored r=new Raspored();
	   if(smena.equals(Smena.PRVA)) {
	    r.setSmena(Smena.PRVA);
	   } else {
	    r.setSmena(Smena.DRUGA);
	   }
	   
	   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	   try {
	    java.util.Date datRez = sdf.parse(datum);
	       java.sql.Date sqlDate = new java.sql.Date(datRez.getTime());
	       r.setDatum(sqlDate);
	   }
	   catch(ParseException e) {
	    e.printStackTrace();
	    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	   }
	   
	   Reon reon=reonService.getReon(idReona);
	   r.setReon(reon);
	   
	   Radnik rad=radnikService.getRadnik(idRadnika);
	   r.setKonobar(rad);
	   
	   rasporedService.sacuvaj(r);
	   
	   List<Radnik> svi=radnikService.getAllRadnik();
	   List<Radnik> togMen=new ArrayList<Radnik>();
	   
	   MenadzerRestorana mr=menadzerService.getMenadzerRestorana(idMen);
	   Restoran uKomJe=mr.getRestoran();
	   
	   for(Radnik jedan: svi) {
	    if(jedan.getRestoran().equals(uKomJe)) {
	     togMen.add(jedan);
	    }
	   }
	   
	   return new ResponseEntity<List<Radnik>>(togMen,HttpStatus.OK);
	   
	   
	  }
	 
	
}
