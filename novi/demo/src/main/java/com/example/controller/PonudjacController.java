package com.example.controller;

import java.sql.Date;
import java.time.LocalDate;
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

import com.example.enumeracije.StatusPonude;
import com.example.enumeracije.StatusPonudePonudjac;
import com.example.enumeracije.VrsteKorisnika;
import com.example.model.Korisnik;
import com.example.model.MenadzerRestorana;
import com.example.model.Ponuda;
import com.example.model.PonudaPonudjac;
import com.example.model.Ponudjac;
import com.example.service.KorisnikService;
import com.example.service.MenadzerRestoranaService;
import com.example.service.PonudaPonudjacService;
import com.example.service.PonudaService;
import com.example.service.PonudjacService;

@RequestMapping(value="/ponudjac")
@Controller
public class PonudjacController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PonudjacService ponudjacService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	MenadzerRestoranaService menadzerService;
	
	@Autowired
	PonudaPonudjacService ponudaPonudjacService;
	
	@Autowired
	PonudaService ponudaService;
	
	//Registrovanje ponudjaca, tj. njegovo dodavanje u sistem
	@RequestMapping(value="/dodajPonudjaca/{idMenadzera}",method = RequestMethod.POST)
	public ResponseEntity<Ponudjac> dodajPonudjaca(@RequestBody Ponudjac ponudjac, @PathVariable Long idMenadzera){
		
		logger.info("usao u dodaj ponudjaca");
		List<Korisnik> korisnici=korisnikService.getAllKorisnik();
		MenadzerRestorana menadzer=menadzerService.getMenadzerRestorana(idMenadzera);
		boolean okej=true;
		for(Korisnik k:korisnici){
			if(k.getKorisnickoIme().equals(ponudjac.getKorisnickoIme()) || k.getEmail().equals(ponudjac.getEmail())){
				okej=false;
			}
		}
		if(okej){
			ponudjac.setMenadzerRestorana(menadzer);
			ponudjac.setVrstaKorisnika(VrsteKorisnika.PONUDJAC);
			ponudjac.setUlogovanPrviPut(true);
			logger.info("Usao u ok " + menadzer.getIme());
			ponudjacService.sacuvaj(ponudjac);
			korisnikService.sacuvaj(ponudjac);
			return new ResponseEntity<Ponudjac>(ponudjac,HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	//Ponudjac dodaje svoju ponudu na ponudu od menadzera, ovo moze tek nakon poziva
	//da se prikazu sve aktivne ponude menadzera, kako bi se prikazale samo trenutno aktivne
	@RequestMapping(value="/dodajPonudu/{idPonudjac}/{idPonude}",method = RequestMethod.POST)
	public ResponseEntity<PonudaPonudjac> dodajPonuduPonudjac(@RequestBody PonudaPonudjac ponudaPonudjac, @PathVariable Long idPonudjac, @PathVariable Long idPonude){
		
		Ponuda ponuda=ponudaService.getPonuda(idPonude);
		
		
		Ponudjac ponudjac=ponudjacService.getPonudjac(idPonudjac);
		ponudaPonudjac.setPonudjac(ponudjac);
		ponudaPonudjac.setStatus(StatusPonudePonudjac.NOVA);
		ponudaPonudjac.setPonuda(ponuda);
		
		ponudaPonudjacService.sacuvaj(ponudaPonudjac);
		
		return new ResponseEntity<PonudaPonudjac>(ponudaPonudjac, HttpStatus.CREATED);
	}
	
	//Ponudjac menja svoju ponudu, ali samo ako je ta ponuda i dalje aktivna, PROVERITI!!!
	@RequestMapping(value="/izmeniPonudu/{idPonudaPonudjac}/{idPonudjac}/{idPonude}",method = RequestMethod.PUT)
	public ResponseEntity<PonudaPonudjac> izmeniPonuduPonudjac(@RequestBody PonudaPonudjac ponudaPonudjac, @PathVariable Long idPonudaPonudjac, @PathVariable Long idPonudjac, @PathVariable Long idPonude){
		
		//PonudaPonudjac zaIzmenu=ponudaPonudjacService.getPonudaPonudjac(idPonudaPonudjac);
		Ponuda ponuda=ponudaService.getPonuda(idPonude);
		
		List<Ponuda> ponude=ponudaService.getAllPonuda();
		List<Ponuda> njegovePonude=new ArrayList<Ponuda>();
		List<Ponuda> zaPrikaz=new ArrayList<Ponuda>();
		
		for(Ponuda p: ponude){
			if(p.getMenadzerRestorana().getKorisnickoIme().equals(ponuda.getMenadzerRestorana().getKorisnickoIme())){
				njegovePonude.add(p);
			}
		}
		
		LocalDate date=LocalDate.now();
		for(Ponuda pon:njegovePonude){
			if(pon.getAktivnoDo().after(Date.valueOf(date))){
				pon.setId(pon.getId());
				pon.setStatus(StatusPonude.ISTEKLA);
				ponudaService.sacuvaj(pon);
			}
		}
		
		List<Ponuda> sve1=ponudaService.getAllPonuda();
		List<Ponuda> njegove1=new ArrayList<Ponuda>();
		
		for(Ponuda p: sve1){
			if(p.getMenadzerRestorana().getKorisnickoIme().equals(ponuda.getMenadzerRestorana().getKorisnickoIme())){
				njegove1.add(p);
			}
		}
		
		for(Ponuda p:njegove1){
			if(p.getStatus().equals(StatusPonude.U_TOKU)){
				zaPrikaz.add(p);
			}
		}
		
		for(Ponuda p:zaPrikaz){
			if(p.getId().equals(ponuda.getId())){
				ponudaPonudjac.setId(idPonudaPonudjac);
				ponudaPonudjacService.sacuvaj(ponudaPonudjac);
				return new ResponseEntity<PonudaPonudjac>(ponudaPonudjac, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
	}
	
	//Menadzer prihvata ponudu od ponudjaca
	@RequestMapping(value="/prihvatiPonudu/{idPonudaPonudjac}/{idPonude}",method = RequestMethod.PUT)
	public ResponseEntity<PonudaPonudjac> prihvatiPonuduOdPonudjaca(@PathVariable Long idPonudaPonudjac, @PathVariable Long idPonude){
		//Porveriti da li treba neke provere, jer moze da prihvati sam pre DO datuma,
		//mada mislim da ne treba, jer je namesteno da mu se prikazuju samo aktivne ponude
		
		PonudaPonudjac ponudaPonudjac=ponudaPonudjacService.getPonudaPonudjac(idPonudaPonudjac);
		ponudaPonudjac.setId(idPonudaPonudjac);
		ponudaPonudjac.setStatus(StatusPonudePonudjac.PRIHAVCENA);
		ponudaPonudjacService.sacuvaj(ponudaPonudjac);
		
		Ponuda ponuda= ponudaService.getPonuda(idPonude);
		ponuda.setId(idPonude);
		ponuda.setStatus(StatusPonude.ISTEKLA);
		ponudaService.sacuvaj(ponuda);
		
		//ovde vracam ponudjacevu ponudu, a mogu i onu drugu
		return new ResponseEntity<PonudaPonudjac>(ponudaPonudjac, HttpStatus.OK);
		
	}
	
	//Prikazi sve ponude od ponudjaca za izabranu ponudu menadzera, tek odavde kada udje
	//na konkretnu svoju ponudu, moze da prihati neku od ponuda ponudjaca koje ce iskociti
	//pomocu ove metode
	@RequestMapping(value="/prikaziPonudePonudjac/{idPonude}",method = RequestMethod.GET)
	public ResponseEntity<List<PonudaPonudjac>> prikaziPonuduOdPonudjaca(@PathVariable Long idPonude){
	
		Ponuda ponuda=ponudaService.getPonuda(idPonude);
		List<PonudaPonudjac> svePonudjacevePonudeZaPonudu=(List<PonudaPonudjac>) ponuda.getPonudaPonudjac();
		 
		return new ResponseEntity<List<PonudaPonudjac>>(svePonudjacevePonudeZaPonudu, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/resetujSifru/{idPonudjac}/{sifra}",method = RequestMethod.POST)
	public void resetujSifru(@PathVariable Long idPonudjac,@PathVariable String sifra){
		
		Ponudjac ponudjac=ponudjacService.getPonudjac(idPonudjac);
		ponudjac.setSifra(sifra);
		ponudjac.setId(idPonudjac);
		ponudjac.setUlogovanPrviPut(false);
		ponudjacService.sacuvaj(ponudjac);
		
	}
	
	@RequestMapping(value="/izmeniPodatke/{idPonudjac}",method = RequestMethod.PUT)
	public ResponseEntity<Ponudjac> izmeniPodatke(@RequestBody Ponudjac ponudjac, @PathVariable Long idPonudjac){
		
		List<Korisnik> sviKorisnici= korisnikService.getAllKorisnik();
		Korisnik menjan=korisnikService.getKorisnik(idPonudjac);
		sviKorisnici.remove(menjan);
		boolean okej=true;
		for(Korisnik k:sviKorisnici){
			if(k.getKorisnickoIme().equals(ponudjac.getKorisnickoIme()) || k.getEmail().equals(ponudjac.getEmail())){
				
				okej=false;
				break;
			}
		}
		if(okej){
			
			ponudjac.setId(idPonudjac);
			ponudjacService.sacuvaj(ponudjac);
			return new ResponseEntity<Ponudjac>(ponudjac, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	 @RequestMapping(value="/uzmiIstoriju/{idPonudjaca}",method = RequestMethod.GET)
	 public ResponseEntity<List<PonudaPonudjac>> uzmiIstoriju(@PathVariable Long idPonudjaca){
	  
	  Ponudjac ponudjac=ponudjacService.getPonudjac(idPonudjaca);
	  List<PonudaPonudjac> njegovePonude=ponudjac.getPonudePonudjac();
	  
	  return new ResponseEntity<List<PonudaPonudjac>>(njegovePonude, HttpStatus.OK);
	  
	 }
	 
}
