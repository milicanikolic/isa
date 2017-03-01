package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Namirnica;
import com.example.service.NamirnicaService;


@RequestMapping(value="/namirnica")
@Controller
public class NamirnicaController {

	
	@Autowired
	NamirnicaService namirnicaService;
	
	@RequestMapping(value="/dodajNamirnicu",method = RequestMethod.POST)
	 public ResponseEntity<List<Namirnica>> dodajNamirnicu(@RequestBody Namirnica namirnica){
	  
	  List<Namirnica> sveNamirnice=namirnicaService.getAllNamirnica();
	 
	  boolean okej=true;
	  for(Namirnica n:sveNamirnice){
	   if(n.getIme().equals(namirnica.getIme())){
	    okej=false;
	   }
	  }
	  
	  if(okej){
	   namirnicaService.sacuvaj(namirnica);
	   
	   List<Namirnica> ponovo= namirnicaService.getAllNamirnica();
	   return new ResponseEntity<List<Namirnica>>(ponovo, HttpStatus.CREATED);
	  }else{
		  List<Namirnica> ponovo1= namirnicaService.getAllNamirnica();
	   return new ResponseEntity<List<Namirnica>>(ponovo1, HttpStatus.BAD_REQUEST);
	  }
	  
	 }
	
	@RequestMapping(value="/izbrisiNamirnicu/{idNamirnica}",method = RequestMethod.DELETE)
	public ResponseEntity<Namirnica> dodajNamirnicu(@RequestBody Namirnica namirnica, @PathVariable Long idNamirnica){
		
		List<Namirnica> sveNamirnice=namirnicaService.getAllNamirnica();
		boolean postoji=false;
		for(Namirnica n:sveNamirnice){
			if(n.getId().equals(idNamirnica)){
				postoji=true;
				break;
			}
		}
		
		if(postoji){
			namirnicaService.izbrisi(namirnica);
			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	
	}
	
	 @RequestMapping(value="/prikaziNamirnice",method = RequestMethod.GET)
	 public ResponseEntity<List<Namirnica>> prikaziNamirnice(){
	 
	  List<Namirnica> namirnice= namirnicaService.getAllNamirnica();
	  if(namirnice.isEmpty()){
	   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	  }else{
	   return new ResponseEntity<List<Namirnica>>(namirnice, HttpStatus.OK);
	  }
	 }
	
	
}
