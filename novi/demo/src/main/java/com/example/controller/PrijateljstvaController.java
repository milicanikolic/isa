package com.example.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	
	
	@Autowired
	private GostService gostService;
	
	@Autowired
	private PrijateljstvaService prijateljstvaService;

	@RequestMapping(value="/prikaziKojeNema/{idPosiljaoca}",method = RequestMethod.POST)
	public ResponseEntity<List<Gost>> prikaziPrijateljeKojeNema(@PathVariable Long idPosiljaoca){
	
		List<Prijateljstva> prijateljstva=prijateljstvaService.getAllPrijateljstva();
		List<Gost> sviGosti=gostService.getAllGost();
		List<Gost> oniKojiNisuUPrijateljima=new ArrayList<Gost>();
		List<Gost> njegoviPrijatelji=new ArrayList<Gost>();
		for(Prijateljstva pri:prijateljstva){
			if(pri.getPosiljalac().getId().equals(idPosiljaoca) && pri.getStatus().equals(StatusZahteva.PRIHVACEN)){
				Gost g=gostService.getGost(pri.getPrimalac().getId());
				njegoviPrijatelji.add(g);
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
	
	@RequestMapping(value="/prikaziSve/{idPosiljaoca}",method = RequestMethod.POST)
	public ResponseEntity<List<Gost>> prikazPrijatelja(@PathVariable Long idPosiljaoca){
		
		List<Prijateljstva> prijateljstva=prijateljstvaService.getAllPrijateljstva();	
		List<Gost> njegoviPrijatelji=new ArrayList<Gost>();
		for(Prijateljstva pri:prijateljstva){
			if(pri.getPosiljalac().getId().equals(idPosiljaoca) && pri.getStatus().equals(StatusZahteva.PRIHVACEN)){
				Gost g=gostService.getGost(pri.getPrimalac().getId());
				njegoviPrijatelji.add(g);
			}
		}
		
		return new ResponseEntity<List<Gost>>(njegoviPrijatelji, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/prihvati/{idPrijateljstva}",method = RequestMethod.POST)
	public ResponseEntity<Prijateljstva> prihvatiZahtev(@PathVariable Long idPrijateljstva){
		
		Prijateljstva prijateljstvoZaIzmenu=prijateljstvaService.getPrijateljstvo(idPrijateljstva);
		prijateljstvoZaIzmenu.setId(idPrijateljstva);
		prijateljstvoZaIzmenu.setStatus(StatusZahteva.PRIHVACEN);
		prijateljstvaService.sacuvaj(prijateljstvoZaIzmenu);
		
		return new ResponseEntity<Prijateljstva>(prijateljstvoZaIzmenu, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/odbij/{idPrijateljstva}",method = RequestMethod.POST)
	public ResponseEntity<Prijateljstva> odbijZahtev(@PathVariable Long idPrijateljstva){
		
		Prijateljstva prijateljstvoZaIzmenu=prijateljstvaService.getPrijateljstvo(idPrijateljstva);
		prijateljstvoZaIzmenu.setId(idPrijateljstva);
		prijateljstvoZaIzmenu.setStatus(StatusZahteva.ODBIJEN);
		prijateljstvaService.sacuvaj(prijateljstvoZaIzmenu);
		
		return new ResponseEntity<Prijateljstva>(prijateljstvoZaIzmenu, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/pretrazi/{idPosiljaoca}",method = RequestMethod.POST)
	public ResponseEntity<List<Gost>> pretrazi(@RequestBody Gost gost,@PathVariable Long idPosiljaoca){
		
		List<Prijateljstva> prijateljstva=prijateljstvaService.getAllPrijateljstva();
		List<Gost> sviGosti=gostService.getAllGost();
		List<Gost> oniKojiNisuUPrijateljima=new ArrayList<Gost>();
		List<Gost> njegoviPrijatelji=new ArrayList<Gost>();
		for(Prijateljstva pri:prijateljstva){
			if(pri.getPosiljalac().getId().equals(idPosiljaoca) && pri.getStatus().equals(StatusZahteva.PRIHVACEN)){
				Gost g=gostService.getGost(pri.getPrimalac().getId());
				njegoviPrijatelji.add(g);
			}
		}
		
		for(Gost gost1:sviGosti){
			for(Gost prijatelj:njegoviPrijatelji)
			if(!gost1.getId().equals(prijatelj.getId())){
				oniKojiNisuUPrijateljima.add(gost1);
			}
		}
		
		//PRETRAGA
		String ime=gost.getIme();
		String prezime=gost.getPrezime();
		List<Gost> rezPoImenu=new ArrayList<Gost>();
		List<Gost> rezPoPrezimenu= new ArrayList<Gost>();
		List<Gost> rezPoOba= new ArrayList<Gost>();
		//AKO NIJE NISTA UKUCAO
		if(ime.equals("") && prezime.equals("")){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		//AKO NIJE UKUCAO PRZ	
		}else if(!ime.equals("") && prezime.equals("")){
			for(Gost g1:oniKojiNisuUPrijateljima){
				if(g1.getIme().contains(ime)){
					rezPoImenu.add(g1);
				}
			}
			return new ResponseEntity<List<Gost>>(rezPoImenu, HttpStatus.OK);
		//AKO NIJE UKUCAO IME	
		}else if(ime.equals("") && !prezime.equals("")){
			for(Gost g1:oniKojiNisuUPrijateljima){
				if(g1.getPrezime().contains(prezime)){
					rezPoPrezimenu.add(g1);
				}
			}
			return new ResponseEntity<List<Gost>>(rezPoPrezimenu, HttpStatus.OK);
		}else if(!ime.equals("") && !prezime.equals("")){
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

