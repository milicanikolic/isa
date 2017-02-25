package com.example.controller;

import java.util.ArrayList;
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
import com.example.model.Jelo;
import com.example.model.Korisnik;
import com.example.model.MenadzerRestorana;
import com.example.model.Pice;
import com.example.model.Restoran;
import com.example.service.JeloService;
import com.example.service.KorisnikService;
import com.example.service.MenadzerRestoranaService;
import com.example.service.PiceService;
import com.example.service.RestoranService;


@Controller
@RequestMapping("/restoran")
public class RestoranController {

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private RestoranService restoranService;
 
 @Autowired
 private JeloService jeloService;
 
 @Autowired
 private PiceService piceService;
 
 @Autowired
 private MenadzerRestoranaService menadzerService;
 
 @Autowired
 private KorisnikService korisnikService;
 
 @RequestMapping(value="/uzmiRestoran/{id}",method = RequestMethod.GET)
 public ResponseEntity<Restoran> uzmiRestoran(@PathVariable Long id){
	 Restoran restoran=restoranService.getRestoran(id);
	 if(restoran!=null){
		 return new ResponseEntity<Restoran>(restoran,HttpStatus.OK);
	 }else{
		 return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	 }
 }
 
 @RequestMapping(value="/uzmiSve",method = RequestMethod.GET)
 public ResponseEntity<List<Restoran>> uzmiSve(){
	 List<Restoran> restorani=restoranService.getAllRestoran();
	 return new ResponseEntity<List<Restoran>>(restorani,HttpStatus.OK);
 }
 @RequestMapping(value="/dodaj",method = RequestMethod.POST)
 public ResponseEntity<Restoran> dodajRestoran(@RequestBody Restoran restoran){
  
  
  
  restoranService.sacuvaj(restoran);
 
 return new ResponseEntity<Restoran>(restoran,HttpStatus.CREATED);
 }
 
 @RequestMapping(value="/izmeni/{id}",method = RequestMethod.PUT)
 public ResponseEntity<Restoran> izmeniRestoran(@PathVariable Long id, @RequestBody Restoran restoran){
  Restoran restoranZaIzmenu= restoranService.getRestoran(id);
  restoran.setId(restoranZaIzmenu.getId());
  restoranService.sacuvaj(restoran);
 
 return new ResponseEntity<Restoran>(restoran,HttpStatus.CREATED);
 }
 
 @RequestMapping(value="/izmeniJelo/{restoran}/{jelo}",method = RequestMethod.PUT)
 public ResponseEntity<Jelo> izmeniJelo(@PathVariable("restoran") Long idRestoran,@PathVariable("jelo") Long idJelo, @RequestBody Jelo jelo){
  List<Jelo> svaJela=jeloService.getAllJelo();
  List<Jelo> jelaTogRestorana= new ArrayList<Jelo>();
  Restoran trenutniRestoran= restoranService.getRestoran(idRestoran);
  boolean ispravno=true;
  for(Jelo j:svaJela){
   if(trenutniRestoran.getIme().equals(jelo.getRestoran().getIme())){
    jelaTogRestorana.add(j);
   }
  }
  
  for(Jelo j1:jelaTogRestorana){
   if(j1.getNaziv().equals(jelo.getNaziv())){
    ispravno=false;
    break;
   }
  }
  if(ispravno){
   Jelo jeloZaIzmenu=jeloService.getJelo(idJelo);
   jelo.setId(jeloZaIzmenu.getId());
   jeloService.sacuvaj(jelo);
   return new ResponseEntity<Jelo>(jelo,HttpStatus.CREATED);
  }else{
   return new ResponseEntity<Jelo>(jelo,HttpStatus.BAD_REQUEST);
  }
  
 }
 
 
 @RequestMapping(value="/izmeniPice/{restoran}/{pice}",method = RequestMethod.PUT)
 public ResponseEntity<Pice> izmeniPice(@PathVariable("restoran") Long idRestoran,@PathVariable("pice") Long idPica, @RequestBody Pice pice){
  List<Pice> svaPica=piceService.getAllPice();
  List<Pice> picaTogRestorana= new ArrayList<Pice>();
  Restoran trenutniRestoran= restoranService.getRestoran(idRestoran);
  boolean ispravno=true;
  for(Pice p:svaPica){
   if(trenutniRestoran.getIme().equals(pice.getRestoran().getIme())){
    picaTogRestorana.add(p);
   }
  }
  
  for(Pice p1:picaTogRestorana){
   if(p1.getNaziv().equals(pice.getNaziv())){
    ispravno=false;
    break;
   }
  }
  if(ispravno){
   Pice piceZaIzmenu=piceService.getPice(idPica);
   pice.setId(piceZaIzmenu.getId());
   piceService.sacuvaj(pice);
   return new ResponseEntity<Pice>(pice,HttpStatus.CREATED);
  }else{
   return new ResponseEntity<Pice>(pice,HttpStatus.BAD_REQUEST);
  }
  
 }
 
 @RequestMapping(value="/dodajMenadzera/{imeRestorana}",method = RequestMethod.POST)
 public ResponseEntity<MenadzerRestorana> dodajMenadzera(@RequestBody MenadzerRestorana menadzer,@PathVariable String imeRestorana){
	 logger.info("usao u dodajmenadjeradas");
	 Restoran restoran=null;
	 for(Restoran r:restoranService.getAllRestoran()){
		 if(r.getIme().equals(imeRestorana)){
			 restoran=r;
			 break;
		 }
	 }
	 
  List<Korisnik> sviKorisnici=korisnikService.getAllKorisnik();
  boolean ispravno=true;
  for(Korisnik m:sviKorisnici){
	   if(m.getKorisnickoIme().equals(menadzer.getKorisnickoIme()) || m.getEmail().equals(menadzer.getEmail())){
	    ispravno=false;
	    break;
	   }
	  }
  if(ispravno){
   menadzer.setVrstaKorisnika(VrsteKorisnika.MENADZER_RESTORANA);
   menadzer.setRestoran(restoran);
   menadzerService.sacuvaj(menadzer);
   korisnikService.sacuvaj(menadzer);
   return new ResponseEntity<MenadzerRestorana>(menadzer,HttpStatus.CREATED);
  }else{
 
  return new ResponseEntity<MenadzerRestorana>(menadzer,HttpStatus.BAD_REQUEST);
  }
 }
 
 
 @RequestMapping(value="/dodajJelo/{id}",method = RequestMethod.POST)
 public ResponseEntity<Jelo> dodajJelo(@RequestBody Jelo jelo,@PathVariable Long idRestorana){
  
  Restoran trenutniRestoran=restoranService.getRestoran(idRestorana);
  List<Jelo> svaJela=jeloService.getAllJelo();
  List<Jelo> jelaURestoranu=new ArrayList<Jelo>();
  boolean okej=true;
  
  for(Jelo j:svaJela){
   if(trenutniRestoran.getIme().equals(j.getRestoran().getIme())){
    jelaURestoranu.add(j);
   }
  }
  
  for(Jelo jel:jelaURestoranu){
   if(jel.getNaziv().equals(jelo.getNaziv())){
    okej=false;
    break;
   }
  }
  
  if(okej){
	  jelo.setRestoran(trenutniRestoran);
   jeloService.sacuvaj(jelo);
   return new ResponseEntity<Jelo>(jelo, HttpStatus.CREATED);
  }else{
   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }
  
 }
 
 

 @RequestMapping(value="/dodajPice/{id}",method = RequestMethod.POST)
 public ResponseEntity<Pice> dodajPice(@RequestBody Pice pice,@PathVariable Long idRestorana){
  
  Restoran trenutniRestoran=restoranService.getRestoran(idRestorana);
  List<Pice> svaPica=piceService.getAllPice();
  List<Pice> picaURestoranu=new ArrayList<Pice>();
  boolean okej=true;
  
  for(Pice p:svaPica){
   if(trenutniRestoran.getIme().equals(p.getRestoran().getIme())){
    picaURestoranu.add(p);
   }
  }
  
  for(Pice pic:picaURestoranu){
   if(pic.getNaziv().equals(pice.getNaziv())){
    okej=false;
    break;
   }
  }
  
  if(okej){
	  pice.setRestoran(trenutniRestoran);
   piceService.sacuvaj(pice);
   return new ResponseEntity<Pice>(pice, HttpStatus.CREATED);
  }else{
   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }
  
 }
 
}