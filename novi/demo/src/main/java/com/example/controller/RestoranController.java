package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
private HttpSession sesija;
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
  
  restoran.setBrojOcenaRes(0);
  restoran.setOcena(0.0);
  
  restoranService.sacuvaj(restoran);
 
 return new ResponseEntity<Restoran>(restoran,HttpStatus.CREATED);
 }
 
 @RequestMapping(value="/postaviRestoran/{id}",method = RequestMethod.POST)
 public ResponseEntity<Restoran> postaviRestoran(@PathVariable Long id){
	 Restoran restoran=restoranService.getRestoran(id);
	 
	 if(restoran!=null){
		 sesija.setAttribute("restoran", restoran);
		 return new ResponseEntity<Restoran>(restoran,HttpStatus.OK);
	 }else{
		 sesija.setAttribute("restoran", null);
		 logger.info("SESIJA: restoran null");
		 return new ResponseEntity<>(null,HttpStatus.OK);
	 }
 }
 
 @RequestMapping(value="/postaviRestoranMR/{korisnicko}",method = RequestMethod.POST)
 public ResponseEntity<Restoran> postaviRestoran(@PathVariable String korisnicko){
	 
	 Restoran restoran=null;
	 
	 Korisnik kori=korisnikService.getKorisnikByKorisnickoIme(korisnicko);
	 if(kori.getVrstaKorisnika().equals(VrsteKorisnika.MENADZER_RESTORANA)){
		 MenadzerRestorana MR=menadzerService.getMenadzerByKorisnickoIme(korisnicko);
		 restoran=MR.getRestoran();
	 }
	 
	 
	 if(restoran!=null){
		
		 sesija.setAttribute("restoran", restoran);
		 logger.info("SESIJA: postavljen restoran: "+restoran.getIme());
		 return new ResponseEntity<Restoran>(restoran,HttpStatus.OK);
	 }else{
		 sesija.setAttribute("restoran", null);
		 return new ResponseEntity<>(null,HttpStatus.OK);
	 }
 }
 
 @RequestMapping(value="/uzmiRestoran",method = RequestMethod.GET)
 public ResponseEntity<Restoran> uzmiRestoran(){
	 Restoran restoran=(Restoran) sesija.getAttribute("restoran");
	 
	 if(restoran!=null){
		 logger.info("sa sesije nije null: ");
		 return new ResponseEntity<Restoran>(restoran,HttpStatus.OK);
	 }else{
		 logger.info("sa sesije je null: ");
		 return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);
	 }
 }
 
 
 @RequestMapping(value="/izmeni/{id}",method = RequestMethod.PUT)
 public ResponseEntity<Restoran> izmeniRestoran(@PathVariable Long id, @RequestBody Restoran restoran){
  Restoran restoranZaIzmenu= restoranService.getRestoran(id);
  restoran.setId(restoranZaIzmenu.getId());
  restoran.setBrojOcenaRes(restoranZaIzmenu.getBrojOcenaRes());
  restoran.setOcena(restoranZaIzmenu.getOcena());
  restoranService.sacuvaj(restoran);
 
 return new ResponseEntity<Restoran>(restoran,HttpStatus.CREATED);
 }
 
 @RequestMapping(value="/izmeniJelo/{jelo}",method = RequestMethod.PUT)
 public ResponseEntity<List<Jelo>> izmeniJelo(@PathVariable("jelo") Long idJelo, @RequestBody Jelo jelo){
  List<Jelo> svaJela=jeloService.getAllJelo();
  List<Jelo> jelaTogRestorana= new ArrayList<Jelo>();
  Restoran trenutniRestoran= jelo.getRestoran();
  boolean ispravno=true;
  for(Jelo j:svaJela){
   if(trenutniRestoran.getId()==(j.getRestoran().getId())){
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
   jelo.setOcena(jeloZaIzmenu.getOcena());
   jelo.setBrojOcenaJelo(jeloZaIzmenu.getBrojOcenaJelo());
   jelo.setId(jeloZaIzmenu.getId());
   jeloService.sacuvaj(jelo);
   
   List<Jelo> saIzmenjenim=jeloService.getAllJelo();
   List<Jelo> jelaTogRestorana1= new ArrayList<Jelo>();
   for(Jelo j:saIzmenjenim){
    if(trenutniRestoran.getId()==(j.getRestoran().getId())){
     jelaTogRestorana1.add(j);
    }
   }
   
   return new ResponseEntity<List<Jelo>>(jelaTogRestorana1,HttpStatus.CREATED);
  }else{
   return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
  }
  
 }
 
 @RequestMapping(value="/izbrisiJelo/{id}",method = RequestMethod.DELETE)
 public ResponseEntity<List<Jelo>> izbrisiJelo(@PathVariable Long id){
  
  List<Jelo> svaJela=jeloService.getAllJelo();
  Jelo jeloZaBrisanje=jeloService.getJelo(id);
  boolean postoji=false;
  
  for(Jelo j:svaJela){
   if(j.getId()==id){
    postoji=true;
   }
  }
  
  if(postoji){
   Restoran trenutniRestoran=jeloZaBrisanje.getRestoran();  
	  
   jeloService.izbrisi(jeloZaBrisanje);
   
   List<Jelo> saIzmenjenim=jeloService.getAllJelo();
   List<Jelo> jelaTogRestorana1= new ArrayList<Jelo>();
   for(Jelo j:saIzmenjenim){
    if(trenutniRestoran.getId()==(j.getRestoran().getId())){
     jelaTogRestorana1.add(j);
    }
   }
   
   return new ResponseEntity<List<Jelo>>(jelaTogRestorana1, HttpStatus.OK);
   
  }else{
   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }
 }
 
 @RequestMapping(value="/uzmiPice/{idPica}",method = RequestMethod.GET)
 public ResponseEntity<Pice> uzmiPice(@PathVariable Long idPica){
	 Pice pice=piceService.getPice(idPica);
	 
	 if(pice!=null){
		 return new ResponseEntity<Pice>(pice,HttpStatus.OK);
	 }else{
		 return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	 }
 }
 
 
 @RequestMapping(value="/izmeniPice/{idPica}",method = RequestMethod.PUT)
 public ResponseEntity<List<Pice>> izmeniPice(@PathVariable Long idPica, @RequestBody Pice pice){

	 logger.info("usao u izmenu pica");
	 
  List<Pice> svaPica=piceService.getAllPice();
  List<Pice> picaTogRestorana= new ArrayList<Pice>();
  Restoran trenutniRestoran= pice.getRestoran();
  boolean ispravno=true;
  for(Pice p:svaPica){
   if(trenutniRestoran.getId()==(p.getRestoran().getId())){
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
   //PICA TOG RESTORANA SA IZMENJENIM PICEM
   List<Pice> saIzmenjenim=piceService.getAllPice();
   List<Pice> picaTogRestorana1= new ArrayList<Pice>();
   for(Pice p:saIzmenjenim){
    if(trenutniRestoran.getId()==(p.getRestoran().getId())){
     picaTogRestorana1.add(p);
    }
   }
  
   return new ResponseEntity<List<Pice>>(picaTogRestorana1,HttpStatus.CREATED);
  }else{
   return new ResponseEntity<List<Pice>>(picaTogRestorana,HttpStatus.BAD_REQUEST);
  }
  
 }
 
 @RequestMapping(value="/izbrisiPice/{id}",method = RequestMethod.DELETE)
 public ResponseEntity<List<Pice>> izbrisiPice(@PathVariable Long id){
  
  List<Pice> svaPica=piceService.getAllPice();
  Pice piceZaBrisanje=piceService.getPice(id);
  
  
 
  boolean postoji=false;
  
  for(Pice p:svaPica){
   if(p.getId()==id){
    postoji=true;
   }
  }
  
  if(postoji){
   Restoran trenutniRestoran=piceZaBrisanje.getRestoran();	  
    
   piceService.izbrisi(piceZaBrisanje);
  
   List<Pice> saIzmenjenim=piceService.getAllPice();
   List<Pice> picaTogRestorana1= new ArrayList<Pice>();
   for(Pice p:saIzmenjenim){
    if(trenutniRestoran.getId()==(p.getRestoran().getId())){
     picaTogRestorana1.add(p);
    }
   }
   
   return new ResponseEntity<List<Pice>>(picaTogRestorana1, HttpStatus.OK);
   
  }else{
   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  } 
 }
 
 @RequestMapping(value="/dodajMenadzera/{idRestorana}",method = RequestMethod.POST)
 public ResponseEntity<MenadzerRestorana> dodajMenadzera(@RequestBody MenadzerRestorana menadzer,@PathVariable Long idRestorana){
	 logger.info("usao u dodajmenadjeradas");
	 Restoran restoran=restoranService.getRestoran(idRestorana);
	
	 //provera sa null
	
  List<Korisnik> sviKorisnici=korisnikService.getAllKorisnik();
  boolean ispravno=true;
  if(restoran!=null){
  for(Korisnik m:sviKorisnici){
	   if(m.getKorisnickoIme().equals(menadzer.getKorisnickoIme()) || m.getEmail().equals(menadzer.getEmail())){
	    ispravno=false;
	    break;
	   }
	  }
	 }else{
		 ispravno=false;
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
 
 
 @RequestMapping(value="/uzmiJelo/{idJela}",method = RequestMethod.GET)
 public ResponseEntity<Jelo> uzmiJelo(@PathVariable Long idJela){
	 Jelo jelo=jeloService.getJelo(idJela);
	 
	 if(jelo!=null){
		 return new ResponseEntity<Jelo>(jelo,HttpStatus.OK);
	 }else{
		 return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	 }
 }
 
 @RequestMapping(value="/dodajJelo/{idRestorana}",method = RequestMethod.POST)
 public ResponseEntity<List<Jelo>> dodajJelo(@RequestBody Jelo jelo,@PathVariable Long idRestorana){
  
  Restoran trenutniRestoran=restoranService.getRestoran(idRestorana);
  List<Jelo> svaJela=jeloService.getAllJelo();
  List<Jelo> jelaURestoranu=new ArrayList<Jelo>();
  jelo.setBrojOcenaJelo(0);
  jelo.setOcena(0.0);
  boolean okej=true;
  
  if(trenutniRestoran!=null){
  
  for(Jelo j:svaJela){
   if(trenutniRestoran.getId()==(j.getRestoran().getId())){
    jelaURestoranu.add(j);
   }
  }
  
  for(Jelo jel:jelaURestoranu){
   if(jel.getNaziv().equals(jelo.getNaziv())){
    okej=false;
    break;
   }
  }
  }else{
	  okej=false;
  }
  
  if(okej){
	  jelo.setRestoran(trenutniRestoran);
	  jeloService.sacuvaj(jelo);
	  
	  List<Jelo> svaJela1=jeloService.getAllJelo();
	  List<Jelo> jelaURestoranu1=new ArrayList<Jelo>();

	  for(Jelo j:svaJela1){
	   if(trenutniRestoran.getId()==(j.getRestoran().getId())){
	    jelaURestoranu1.add(j);
	   }
	  }
	  
   return new ResponseEntity<List<Jelo>>(jelaURestoranu1, HttpStatus.CREATED);
  }else{
   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }
  
 }
 
 @RequestMapping(value="/jelovnik/{idRestorana}",method = RequestMethod.GET)
 public ResponseEntity<List<Jelo>> uzmiJelovnik(@PathVariable Long idRestorana){
	 Restoran restoran=restoranService.getRestoran(idRestorana);
	 if(restoran!=null){
		 List<Jelo>svaJela=jeloService.getAllJelo();
		 List<Jelo> jelovnik=new ArrayList<Jelo>();
		 for(Jelo j:svaJela){
			 if(j.getRestoran().getId()==idRestorana){
				 jelovnik.add(j);
			 }
		 }
		 return new ResponseEntity<List<Jelo>>(jelovnik, HttpStatus.OK);
	 }else{
		 return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	 }
	 
 }

 @RequestMapping(value="/uzmiJelovnik",method = RequestMethod.GET)
 public ResponseEntity<List<Jelo>> jjjelovnik(){
	 Restoran r=(Restoran) sesija.getAttribute("restoran");
	 Restoran restoran=restoranService.getRestoran(r.getId());
	 if(restoran!=null){
		 List<Jelo>svaJela=jeloService.getAllJelo();
		 List<Jelo> jelovnik=new ArrayList<Jelo>();
		 for(Jelo j:svaJela){
			 if(j.getRestoran().getId()==restoran.getId()){
				 jelovnik.add(j);
			 }
		 }
		 return new ResponseEntity<List<Jelo>>(jelovnik, HttpStatus.OK);
	 }else{
		 return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	 }
	 
 }
 
 @RequestMapping(value="/kartaPica",method = RequestMethod.GET)
 public ResponseEntity<List<Pice>> uzmiKartuPica(){
	 Restoran r=(Restoran) sesija.getAttribute("restoran");
  Restoran restoran=restoranService.getRestoran(r.getId());
  if(restoran!=null){
   List<Pice>svaPica=piceService.getAllPice();
   List<Pice> kartaPica=new ArrayList<Pice>();
   for(Pice p:svaPica){
    if(p.getRestoran().getId()==restoran.getId()){
     kartaPica.add(p);
    }
   }
   return new ResponseEntity<List<Pice>>(kartaPica, HttpStatus.OK);
  }else{
   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }
  
 }
 
 
 @RequestMapping(value="/dodajPice/{idRestorana}",method = RequestMethod.POST)
 public ResponseEntity<List<Pice>> dodajPice(@RequestBody Pice pice,@PathVariable Long idRestorana){
  
  Restoran trenutniRestoran=restoranService.getRestoran(idRestorana);
  List<Pice> svaPica=piceService.getAllPice();
  List<Pice> picaURestoranu=new ArrayList<Pice>();
  boolean okej=true;
  
  if(trenutniRestoran!=null){
  
  for(Pice p:svaPica){
   if(trenutniRestoran.getId()==(p.getRestoran().getId())){
    picaURestoranu.add(p);
   }
  }
  
  for(Pice pic:picaURestoranu){
   if(pic.getNaziv().equals(pice.getNaziv())){
    okej=false;
    break;
   }
  }
  }else{
	  okej=false;
  }
  
  if(okej){
	  pice.setRestoran(trenutniRestoran);
	  piceService.sacuvaj(pice);
	  
	  List<Pice> svaPica1=piceService.getAllPice();
	  List<Pice> piceURestoranu1=new ArrayList<Pice>();

	  for(Pice p:svaPica1){
	   if(trenutniRestoran.getId()==(p.getRestoran().getId())){
	    piceURestoranu1.add(p);
	   }
	  }
	  
	  
   return new ResponseEntity<List<Pice>>(piceURestoranu1, HttpStatus.CREATED);
  }else{
   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }
  
 }
 
}