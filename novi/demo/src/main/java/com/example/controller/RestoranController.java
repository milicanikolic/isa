package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.enumeracije.VrsteKorisnika;
import com.example.model.Jelo;
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
 
 @RequestMapping(value="/dodaj",method = RequestMethod.PUT)
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
 
 @RequestMapping(value="/dodajMenadzera",method = RequestMethod.POST)
 public ResponseEntity<MenadzerRestorana> dodajMenadzera(@RequestBody MenadzerRestorana menadzer){
  List<MenadzerRestorana> sviMenadzeri=menadzerService.getAllMenadzeri();
  boolean ispravno=true;
  for(MenadzerRestorana m:sviMenadzeri){
   if(m.getKorisnickoIme().equals(menadzer.getKorisnickoIme()) || m.getEmail().equals(menadzer.getEmail())){
    ispravno=false;
    break;
   }
  }
  if(ispravno){
   menadzer.setVrstaKorisnika(VrsteKorisnika.MENADZER_RESTORANA);
   menadzerService.sacuvaj(menadzer);
   korisnikService.sacuvaj(menadzer);
   return new ResponseEntity<MenadzerRestorana>(menadzer,HttpStatus.CREATED);
  }else{
 
  return new ResponseEntity<MenadzerRestorana>(menadzer,HttpStatus.BAD_REQUEST);
  }
 }
 
 
}