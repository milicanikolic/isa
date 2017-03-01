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

import com.example.enumeracije.StatusZahteva;
import com.example.model.Gost;
import com.example.model.Prijateljstva;
import com.example.repository.GostRepository;
import com.example.service.GostService;
import com.example.service.PrijateljstvaService;

@Controller
@RequestMapping(value="/prijatelji")
public class PrijateljstvaController {
	
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GostService gostService;
	
	@Autowired
	private PrijateljstvaService prijateljstvaService;

	@RequestMapping(value="/prikaziKojeNema/{idPosiljaoca}",method = RequestMethod.GET)
	 public ResponseEntity<List<Gost>> prikaziPrijateljeKojeNema(@PathVariable Long idPosiljaoca){
	 
	  List<Prijateljstva> prijateljstva=prijateljstvaService.getAllPrijateljstva();
	  List<Gost> sviGosti=gostService.getAllGost();
	  sviGosti.remove(gostService.getGost(idPosiljaoca));
	  List<Gost> oniKojiNisuUPrijateljima=new ArrayList<Gost>();
	  List<Gost> njegoviPrijatelji=new ArrayList<Gost>();
	  for(Prijateljstva pri:prijateljstva){
	   if( pri.getStatus().equals(StatusZahteva.PRIHVACEN)){
	    if(pri.getPosiljalac().getId().equals(idPosiljaoca)){
	     Gost g=gostService.getGost(pri.getPrimalac().getId());
	     njegoviPrijatelji.add(g);
	   }else if( pri.getPrimalac().getId().equals(idPosiljaoca)){
		   Gost g=gostService.getGost(pri.getPosiljalac().getId());
		     njegoviPrijatelji.add(g);
	   }
	  }
	 }
	  
	  for(Gost gost:sviGosti){
	   for(Gost prijatelj:njegoviPrijatelji)
	   if(!gost.getId().equals(prijatelj.getId())){
	    oniKojiNisuUPrijateljima.add(gost);
	   }
	  }
	  
	  return new ResponseEntity<List<Gost>>(oniKojiNisuUPrijateljima, HttpStatus.OK);
	  
	 }
	
	@RequestMapping(value="/dodaj/{idPosiljaoca}/{idPrimaoca}",method = RequestMethod.POST)
	public ResponseEntity<Prijateljstva> dodajPrijatelja(@PathVariable Long idPosiljaoca, @PathVariable Long idPrimaoca){
	
		
		Gost posiljalac= gostService.getGost(idPosiljaoca);
		Gost primalac= gostService.getGost(idPrimaoca);
		
		Prijateljstva prijateljstvo = new Prijateljstva(posiljalac,primalac,StatusZahteva.NA_CEKANJU);
		prijateljstvaService.sacuvaj(prijateljstvo);
		
		return new ResponseEntity<Prijateljstva>(prijateljstvo, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/prikaziSve/{idPosiljaoca}",method = RequestMethod.GET)
	 public ResponseEntity<List<Gost>> prikazPrijatelja(@PathVariable Long idPosiljaoca){
	  
	  List<Prijateljstva> prijateljstva=prijateljstvaService.getAllPrijateljstva(); 
	  List<Gost> njegoviPrijatelji=new ArrayList<Gost>();
	  for(Prijateljstva pri:prijateljstva){
	   if( pri.getStatus().equals(StatusZahteva.PRIHVACEN)){
	    if(pri.getPosiljalac().getId()==(idPosiljaoca)){
	    Gost g=gostService.getGost(pri.getPrimalac().getId());
	    njegoviPrijatelji.add(g);
	   }else if(pri.getPrimalac().getId()==(idPosiljaoca)){
		   Gost g=gostService.getGost(pri.getPosiljalac().getId());
		    njegoviPrijatelji.add(g);
	   }
	  }
	 }
	  return new ResponseEntity<List<Gost>>(njegoviPrijatelji, HttpStatus.CREATED); 
	 }
	
	@RequestMapping(value="/prihvati/{idPosiljaoca}/{idPrimaoca}",method = RequestMethod.POST)
	 public ResponseEntity<Gost> prihvatiZahtev(@PathVariable Long idPosiljaoca,@PathVariable Long idPrimaoca){
	  logger.info("posiljalac:  "+idPosiljaoca+" primalacc:  "+idPrimaoca);
	  List<Prijateljstva> prijateljstva=prijateljstvaService.getAllPrijateljstva();
	  Gost g=null;
	  for(Prijateljstva p:prijateljstva){
		  logger.info(p.getPosiljalac().getIme()+"   "+p.getPrimalac().getIme());
	   if(p.getPosiljalac().getId()==idPosiljaoca && p.getPrimalac().getId()==idPrimaoca && p.getStatus().equals(StatusZahteva.NA_CEKANJU)){
	    p.setId(p.getId());
	    p.setStatus(StatusZahteva.PRIHVACEN);
	    logger.info("promenio status");
	    prijateljstvaService.sacuvaj(p);
	    g=p.getPrimalac();
	    break; 
	   }
	  }
	  
	  return new ResponseEntity<Gost>(g, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(value="/odbij/{idPosiljaoca}/{idPrimaoca}",method = RequestMethod.POST)
	 public ResponseEntity<Gost> odbijZahtev(@PathVariable Long idPosiljaoca,@PathVariable Long idPrimaoca){
	  
	  List<Prijateljstva> prijateljstva=prijateljstvaService.getAllPrijateljstva();
	  Gost g=null;
	  for(Prijateljstva p:prijateljstva){
	   if(p.getPosiljalac().getId()==idPosiljaoca && p.getPrimalac().getId()==idPrimaoca && p.getStatus().equals(StatusZahteva.NA_CEKANJU)){
	    p.setId(p.getId());
	    p.setStatus(StatusZahteva.ODBIJEN);
	    prijateljstvaService.sacuvaj(p);
	    g=p.getPrimalac();
	    break; 
	   }
	  }
	  
	  return new ResponseEntity<Gost>(g, HttpStatus.CREATED);
	 }
	
	 @RequestMapping(value="/pretrazi/{idPosiljaoca}",method = RequestMethod.POST)
	 public ResponseEntity<List<Gost>> pretrazi(@RequestBody Gost gost,@PathVariable Long idPosiljaoca){
	  
	  List<Prijateljstva> prijateljstva=prijateljstvaService.getAllPrijateljstva();
	  List<Gost> sviGosti=gostService.getAllGost();
	  sviGosti.remove(gostService.getGost(idPosiljaoca));
	  List<Gost> oniKojiNisuUPrijateljima=new ArrayList<Gost>();
	  List<Gost> njegoviPrijatelji=new ArrayList<Gost>();
	  for(Prijateljstva pri:prijateljstva){
	   if( pri.getStatus().equals(StatusZahteva.PRIHVACEN)){
	    if(pri.getPosiljalac().getId()==(idPosiljaoca)){
	     Gost g=gostService.getGost(pri.getPrimalac().getId());
	     logger.info("gornje dodavanje u prijatelja: "+g.getIme());
	     njegoviPrijatelji.add(g);
	   }else if(pri.getPrimalac().getId().equals(idPosiljaoca)){
		   Gost g=gostService.getGost(pri.getPosiljalac().getId());
		     njegoviPrijatelji.add(g);
		     logger.info("donje dodavanje u prijatelja: "+g.getIme());
	   }
	  }
	 }
	  if(njegoviPrijatelji.size()!=0){
		  oniKojiNisuUPrijateljima=sviGosti;
	   for(Gost prijatelj:njegoviPrijatelji){
	   
		   logger.info(" dodavanje u NEprijatelja: "+prijatelj.getIme());
	    oniKojiNisuUPrijateljima.remove(prijatelj);
	   }

	  }else{
		  logger.info("size prijatelja 0");
	   oniKojiNisuUPrijateljima=sviGosti;
	  }
	  
	  //PRETRAGA
	  String ime=gost.getIme();
	  String prezime=gost.getPrezime();
	  List<Gost> rezPoImenu=new ArrayList<Gost>();
	  List<Gost> rezPoPrezimenu= new ArrayList<Gost>();
	  List<Gost> rezPoOba= new ArrayList<Gost>();
	  //AKO NIJE NISTA UKUCAO
	  if(ime==null && prezime==null){
	   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	  //AKO NIJE UKUCAO PRZ 
	  }else if(ime!=null && prezime==null){
	   for(Gost g1:oniKojiNisuUPrijateljima){
	    if(g1.getIme().contains(ime)){
	     rezPoImenu.add(g1);
	    }
	   }
	   return new ResponseEntity<List<Gost>>(rezPoImenu, HttpStatus.OK);
	  //AKO NIJE UKUCAO IME 
	  }else if(ime==null && prezime!=null){
	   for(Gost g1:oniKojiNisuUPrijateljima){
	    if(g1.getPrezime().contains(prezime)){
	     rezPoPrezimenu.add(g1);
	    }
	   }
	   return new ResponseEntity<List<Gost>>(rezPoPrezimenu, HttpStatus.OK);
	  }else if(ime!=null && prezime!=null){
	   for(Gost g1:oniKojiNisuUPrijateljima){
	    if(g1.getPrezime().contains(prezime) && g1.getIme().contains(ime)){
	     rezPoOba.add(g1);
	    }
	   }
	   return new ResponseEntity<List<Gost>>(rezPoOba, HttpStatus.OK);
	  }
	  
	  return null;  
	 }
	
	
}

