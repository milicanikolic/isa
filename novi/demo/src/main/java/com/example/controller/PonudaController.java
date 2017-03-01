package com.example.controller;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.example.model.MenadzerRestorana;
import com.example.model.Namirnica;
import com.example.model.Ponuda;
import com.example.service.MenadzerRestoranaService;
import com.example.service.NamirnicaService;
import com.example.service.PonudaService;


@RequestMapping(value="/ponuda")
@Controller
public class PonudaController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PonudaService ponudaService;
	
	@Autowired
	private NamirnicaService namirnicaService;
	
	@Autowired
	private MenadzerRestoranaService menadzerService;

	//Dodavanje nove ponude menadzera
	 @RequestMapping(value="/dodajPonuduMenadzera/{idMenadzer}/{idNam1}/{idNam2}/{idNam3}/{datumOd}/{datumDo}",method = RequestMethod.POST)
	 public ResponseEntity<Ponuda> dodajPonudu(@PathVariable Long idMenadzer,@PathVariable Long idNam1,@PathVariable Long idNam2,@PathVariable Long idNam3, @PathVariable String datumOd,@PathVariable String datumDo){
	  
	  MenadzerRestorana menadzer= menadzerService.getMenadzerRestorana(idMenadzer);
	  Ponuda ponuda=new Ponuda();
	  
	   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	   try {
	    java.util.Date datRez1 = sdf.parse(datumOd);
	    java.sql.Date sqlDate1 = new java.sql.Date(datRez1.getTime());
	    java.util.Date datRez2 = sdf.parse(datumDo);
	    java.sql.Date sqlDate2 = new java.sql.Date(datRez2.getTime());
	    ponuda.setAktivnoOd(sqlDate1);
	    ponuda.setAktivnoDo(sqlDate2);
	   } catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	   }
	  
	  
	  Namirnica nam1=namirnicaService.getNamirnica(idNam1);
	  Namirnica nam2=namirnicaService.getNamirnica(idNam2);
	  Namirnica nam3=namirnicaService.getNamirnica(idNam3);
	  Set<Namirnica> namirnica= new HashSet<Namirnica>();
	  
	  namirnica.add(nam1);
	  namirnica.add(nam2);
	  namirnica.add(nam3);
	  
	  ponuda.setNamirnice(namirnica);
	  
	 
	  ponuda.setMenadzerRestorana(menadzer);
	  ponuda.setStatus(StatusPonude.U_TOKU);
	  logger.info("status "+ponuda.getStatus());
	  
	  ponudaService.sacuvaj(ponuda);
	  
	  
	  return new ResponseEntity<Ponuda>(ponuda, HttpStatus.CREATED);
	 }
	//Prikaz svih aktivnih ponuda menadzera, znaci pre nego sto je istekao DO datum
	@RequestMapping(value="/prikaziAktivne/{idMenadzer}",method = RequestMethod.GET)
	public ResponseEntity<List<Ponuda>> prikaziPonude(@PathVariable Long idMenadzer){
		
		List<Ponuda> ponude=ponudaService.getAllPonuda();
		List<Ponuda> njegovePonude=new ArrayList<Ponuda>();
		List<Ponuda> zaPrikaz=new ArrayList<Ponuda>();
	
		
		for(Ponuda p: ponude){
			logger.info("status1: "+p.getStatus());
			if(p.getMenadzerRestorana().getId()==idMenadzer){
				njegovePonude.add(p);
				logger.info("PREPOZNAO MENADZERA, BROJ NJEGOVIH PONUDA " + njegovePonude.size());
			}
		}
		
		LocalDate date=LocalDate.now();
		for(Ponuda pon:njegovePonude){
			if(pon.getAktivnoDo().before(Date.valueOf(date))){
				
				pon.setId(pon.getId());
				pon.setStatus(StatusPonude.ISTEKLA);
				ponudaService.sacuvaj(pon);
				logger.info("USAO U OVO DA JE ISTEKLA");
			}
		}
		
		//Nakon provere da li je istekao danasnji datum i menjanja statusa, ponovo
		//ucitavam sve iz baze da bih prikazala refresovane samo aktivne ponude menadzera
		
		List<Ponuda> sve1=ponudaService.getAllPonuda();
		List<Ponuda> njegove1=new ArrayList<Ponuda>();
		
		for(Ponuda p: sve1){
			if(p.getMenadzerRestorana().getId()==idMenadzer && p.getStatus().equals(StatusPonude.U_TOKU)){
				njegove1.add(p);
				logger.info("NJEGOVE NOVE " + njegove1.size());
			}
		}
		logger.info("njegove: "+njegove1.size());
	/*	for(Ponuda p:njegove1){
			logger.info("status: "+p.getStatus());
			if(p.getStatus().equals(StatusPonude.U_TOKU)){
				zaPrikaz.add(p);
				logger.info("ZA PRIKAZ "+zaPrikaz.size());
			}
		}*/
		
		return new ResponseEntity<List<Ponuda>>(njegove1, HttpStatus.OK);
	}
	
	//Prikaz konkretne ponude menadzera
	@RequestMapping(value="/prikaziPonuduMenadzera/{idPonuda}",method = RequestMethod.GET)
	public ResponseEntity<Ponuda> prikaziPonudu(@PathVariable Long idPonuda){
	
		Ponuda ponuda=ponudaService.getPonuda(idPonuda);
		List<Ponuda> svePonude=ponudaService.getAllPonuda();
		boolean postoji=false;
		for(Ponuda p: svePonude){
			if(p.getId()==idPonuda){
				postoji=true;
			}
		}
		if(postoji){
			return new ResponseEntity<Ponuda>(ponuda, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}

