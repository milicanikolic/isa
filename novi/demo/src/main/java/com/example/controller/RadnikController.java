package com.example.controller;

import java.util.ArrayList;
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

import com.example.enumeracije.StatusJela;
import com.example.enumeracije.StatusNarudzbine;
import com.example.enumeracije.StatusPica;
import com.example.enumeracije.VrstaJela;
import com.example.enumeracije.VrsteKorisnika;
import com.example.model.GostNarudzbina;
import com.example.model.Jelo;
import com.example.model.Kuvar.vrstaKuvara;
import com.example.model.Pice;
import com.example.model.Radnik;
import com.example.service.JeloService;
import com.example.service.KorisnikService;
import com.example.service.NarudzbinaService;
import com.example.service.PiceService;
import com.example.service.RadnikService;


@Controller
@RequestMapping("/radnik")
public class RadnikController {

	
	@Autowired
	private RadnikService radnikService;
	

	@Autowired
	private PiceService piceService;
	
	@Autowired
	private NarudzbinaService narudzbinaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private JeloService jeloService;
		
	
	 @RequestMapping(value="/dodajRadnika/",method = RequestMethod.POST)
	 public ResponseEntity<Radnik> dodajRadnika(@RequestBody Radnik radnik){
	  
	  List<Radnik> sviRadnici= radnikService.getAllRadnik();
	  boolean ispravan=true;
	  
	  for(Radnik r: sviRadnici){
	   if(r.getKorisnickoIme().equals(radnik.getKorisnickoIme()) || r.getEmail().equals(radnik.getEmail())){
	    ispravan= false;
	   }
	  }
	  if(ispravan){
	   radnik.setUlogovanPrviPut(true);
	   if(radnik.getVrstaKorisnika().equals(VrsteKorisnika.KONOBAR)){
		  radnik.setBrojOcenaKonobar(0);
		  radnik.setOcenaKonobara(0.0);
	   }
	   
	   korisnikService.sacuvaj(radnik);
	   radnikService.save(radnik);
	   return new ResponseEntity<Radnik>(radnik,HttpStatus.CREATED);
	  }else{
	   return new ResponseEntity<Radnik>(radnik,HttpStatus.BAD_REQUEST);
	  }
	  
	 }
	 
	 @RequestMapping(value="/resetujSifruRadnik/{idPonudjac}/{sifra}",method = RequestMethod.POST)
	 public void resetujSifru( @PathVariable Long idRadnik,@PathVariable String sifra){
	  
	  Radnik radnik= radnikService.getRadnik(idRadnik);
	  radnik.setSifra(sifra);
	  radnik.setId(idRadnik);
	  radnik.setUlogovanPrviPut(false);
	  radnikService.save(radnik);
	  
	 }
	
	
	
	
	@RequestMapping(value="/izmeniRadnika/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Radnik> izmeniRadnika(@PathVariable Long id,@RequestBody Radnik radnik){
		
		Radnik radnikZaIzmenu=radnikService.getRadnik(id);
		
		
		List<Radnik> sviRadnici= radnikService.getAllRadnik();
		sviRadnici.remove(radnikZaIzmenu);
		boolean ispravan=true;
		
		for(Radnik r: sviRadnici){
			if(r.getKorisnickoIme().equals(radnik.getKorisnickoIme()) || r.getEmail().equals(radnik.getEmail())){
				ispravan= false;
			}
		}
		if(ispravan){
			
			radnik.setId(radnikZaIzmenu.getId());
			if(radnik.getVrstaKorisnika().equals(VrsteKorisnika.KONOBAR)){
				radnik.setOcenaKonobara(radnikZaIzmenu.getOcenaKonobara());
				radnik.setBrojOcenaKonobar(radnikZaIzmenu.getBrojOcenaKonobar());
			}
			korisnikService.sacuvaj(radnik);
			radnikService.save(radnik);
		return new ResponseEntity<Radnik>(radnik,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/dodajNarudzbinu/{idKonobara}",method = RequestMethod.POST)
	public ResponseEntity<List<GostNarudzbina>> dodajNarudzbinu(@RequestBody GostNarudzbina narudzbina, @PathVariable Long idKonobara){
		
	/*	List<Radnik> radnici=radnikService.getAllRadnik();
		Set<Radnik> kuvari=new HashSet<Radnik>();
		Set<Radnik> sankeri=new HashSet<Radnik>();
		Set<Jelo> jela=narudzbina.getJelaNaNarudzbini();
		Set<Pice> pica=narudzbina.getPicaNaNarudzbini();
		
		
		for(Radnik r:radnici) {
			if(r.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR)) {
				kuvari.add(r);
			}
			if(r.getVrstaKorisnika().equals(VrsteKorisnika.SANKER)) {
				sankeri.add(r);
			}
		}*/
		
		Set<Jelo> jela=narudzbina.getJelaNaNarudzbini();
		for(Jelo naNarudzbini:jela)
			naNarudzbini.setStatusJela(StatusJela.NA_CEKANJU);
		
		Set<Pice> pica=narudzbina.getPicaNaNarudzbini();
		for(Pice naNarudzbini:pica)
			naNarudzbini.setStatusPica(StatusPica.NA_CEKANJU);
		
		
		
		//narudzbina.setJeloSpremljeno(false);
		//narudzbina.setPiceSpremljeno(false);
		narudzbina.setStatusNarudzbine(StatusNarudzbine.U_TOKU);
		
		
		
		narudzbinaService.save(narudzbina);
		
		
		
		List<GostNarudzbina> sve=narudzbinaService.getAllNarudzbina();
		List<GostNarudzbina> narudzbineKonobara=new ArrayList<>();
		for(GostNarudzbina jedna:sve) {
			if(jedna.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
			if(jedna.getRadnik().getId()==idKonobara) {
				narudzbineKonobara.add(jedna);
			}
		}
		
		}
		
		return new ResponseEntity<List<GostNarudzbina>>(narudzbineKonobara, HttpStatus.OK);

	}
	
	
	@RequestMapping(value="/kuvarPrikaziJela/{idKuvara}",method = RequestMethod.GET)
	public ResponseEntity<List<Jelo>> kuvarPrikaziJela(@PathVariable Long idKuvara) {
		
		Radnik r=radnikService.getRadnik(idKuvara);
		List<GostNarudzbina> sveNar = narudzbinaService.getAllNarudzbina();
		List<Jelo> zaSpremanje=new ArrayList<Jelo>();
		
		for(GostNarudzbina nar:sveNar) {
			if(nar.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
			for(Jelo j:nar.getJelaNaNarudzbini()){
				if(j.getStatusJela().equals(StatusJela.NA_CEKANJU) || j.getStatusJela().equals(StatusJela.U_PRIPREMI)) {
					zaSpremanje.add(j);
				}
			}
		}
		
		}
		
		
		if(r.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_KUVANA_JELA)) {
			List<Jelo> kuvanaJela=new ArrayList<Jelo>();
			for(Jelo j:zaSpremanje) {
				if(j.getVrstaJela().equals(VrstaJela.KUVANO_JELO)) {
					kuvanaJela.add(j);
				}
			}
			return new ResponseEntity<List<Jelo>>(kuvanaJela,HttpStatus.OK);
			
			}
		
		if(r.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_PECENA_JELA)) {
			List<Jelo> pecenaJela=new ArrayList<Jelo>();
			for(Jelo j:zaSpremanje) {
				if(j.getVrstaJela().equals(VrstaJela.PECENO_JELO)) {
					pecenaJela.add(j);
				}
			}
			return new ResponseEntity<List<Jelo>>(pecenaJela,HttpStatus.OK);
			
			}
		
		if(r.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_SALATE)) {
			List<Jelo> salate=new ArrayList<Jelo>();
			for(Jelo j:zaSpremanje) {
				if(j.getVrstaJela().equals(VrstaJela.SALATA)) {
					salate.add(j);
				}
			}
			return new ResponseEntity<List<Jelo>>(salate,HttpStatus.OK);
			
			}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/kuvarPrihvatiJelo/{idKuvara}/{idJela}",method = RequestMethod.PUT)
	public ResponseEntity<List<Jelo>> kuvarPrihvatiJelo(@PathVariable Long idKuvara, @PathVariable Long idJela){
		
		Jelo jelo=jeloService.getJelo(idJela);
		Set<GostNarudzbina> naNar=jelo.getNaNarudzbinama();
		
		//Radnik kuvar=radnikService.getRadnik(idKuvara);
		
		for(GostNarudzbina n:naNar) {
			if(n.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
				for(Jelo j:n.getJelaNaNarudzbini()) {
					if(j.getId()==idJela) {
						j.setStatusJela(StatusJela.U_PRIPREMI);
						j.setId(idJela);
						jeloService.sacuvaj(j);
						n.setId(n.getId());
						narudzbinaService.save(n);
					}
				}
			}
		}
		
		Radnik r=radnikService.getRadnik(idKuvara);
		List<GostNarudzbina> sveNar = narudzbinaService.getAllNarudzbina();
		List<Jelo> zaSpremanje=new ArrayList<Jelo>();
		
		for(GostNarudzbina nar:sveNar) {
			if(nar.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
			for(Jelo j:nar.getJelaNaNarudzbini()){
				if(j.getStatusJela().equals(StatusJela.NA_CEKANJU) || j.getStatusJela().equals(StatusJela.U_PRIPREMI)) {
					zaSpremanje.add(j);
				}
			}
			}}
		
		
		
		if(r.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_KUVANA_JELA)) {
			List<Jelo> kuvanaJela=new ArrayList<Jelo>();
			for(Jelo j:zaSpremanje) {
				if(j.getVrstaJela().equals(VrstaJela.KUVANO_JELO)) {
					kuvanaJela.add(j);
				}
			}
			return new ResponseEntity<List<Jelo>>(kuvanaJela,HttpStatus.OK);
			
			}
		
		if(r.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_PECENA_JELA)) {
			List<Jelo> pecenaJela=new ArrayList<Jelo>();
			for(Jelo j:zaSpremanje) {
				if(j.getVrstaJela().equals(VrstaJela.PECENO_JELO)) {
					pecenaJela.add(j);
				}
			}
			return new ResponseEntity<List<Jelo>>(pecenaJela,HttpStatus.OK);
			
			}
		
		if(r.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_SALATE)) {
			List<Jelo> salate=new ArrayList<Jelo>();
			for(Jelo j:zaSpremanje) {
				if(j.getVrstaJela().equals(VrstaJela.SALATA)) {
					salate.add(j);
				}
			}
			return new ResponseEntity<List<Jelo>>(salate,HttpStatus.OK);
			
			}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
	}
		
		
	@RequestMapping(value="/kuvarSpremioJelo/{idKuvara}/{idJela}",method = RequestMethod.PUT)
	public ResponseEntity<List<Jelo>> kuvarSpremioJelo(@PathVariable Long idKuvara, @PathVariable Long idJela){

		Jelo jelo=jeloService.getJelo(idJela);
		Set<GostNarudzbina> naNar=jelo.getNaNarudzbinama();
		
		//Radnik kuvar=radnikService.getRadnik(idKuvara);
		
		for(GostNarudzbina n:naNar) {
			if(n.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
				for(Jelo j:n.getJelaNaNarudzbini()) {
					if(j.getId()==idJela) {
						j.setStatusJela(StatusJela.SPREMLJENO);
						j.setId(idJela);
						jeloService.sacuvaj(j);
						n.setId(n.getId());
						narudzbinaService.save(n);
					}
				}
			}
		}
		
		Radnik r=radnikService.getRadnik(idKuvara);
		List<GostNarudzbina> sveNar = narudzbinaService.getAllNarudzbina();
		List<Jelo> zaSpremanje=new ArrayList<Jelo>();
		
		for(GostNarudzbina nar:sveNar) {
			if(nar.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
			for(Jelo j:nar.getJelaNaNarudzbini()){
				if(j.getStatusJela().equals(StatusJela.NA_CEKANJU) || j.getStatusJela().equals(StatusJela.U_PRIPREMI)) {
					zaSpremanje.add(j);
				}
			}
		}
		}
		
		
		
		if(r.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_KUVANA_JELA)) {
			List<Jelo> kuvanaJela=new ArrayList<Jelo>();
			for(Jelo j:zaSpremanje) {
				if(j.getVrstaJela().equals(VrstaJela.KUVANO_JELO)) {
					kuvanaJela.add(j);
				}
			}
			return new ResponseEntity<List<Jelo>>(kuvanaJela,HttpStatus.OK);
			
			}
		
		if(r.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_PECENA_JELA)) {
			List<Jelo> pecenaJela=new ArrayList<Jelo>();
			for(Jelo j:zaSpremanje) {
				if(j.getVrstaJela().equals(VrstaJela.PECENO_JELO)) {
					pecenaJela.add(j);
				}
			}
			return new ResponseEntity<List<Jelo>>(pecenaJela,HttpStatus.OK);
			
			}
		
		if(r.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_SALATE)) {
			List<Jelo> salate=new ArrayList<Jelo>();
			for(Jelo j:zaSpremanje) {
				if(j.getVrstaJela().equals(VrstaJela.SALATA)) {
					salate.add(j);
				}
			}
			return new ResponseEntity<List<Jelo>>(salate,HttpStatus.OK);
			
			}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	@RequestMapping(value="/sankerPrikaziPica",method = RequestMethod.GET)
	public ResponseEntity<List<Pice>> sankerPrikaziPica() {
		
		//Radnik r=radnikService.getRadnik(idSankera);
		List<GostNarudzbina> sveNar = narudzbinaService.getAllNarudzbina();
		List<Pice> zaSpremanje=new ArrayList<Pice>();
		
		for(GostNarudzbina nar:sveNar) {
			
		if(nar.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
			for(Pice p:nar.getPicaNaNarudzbini()){
				if(p.getStatusPica().equals(StatusPica.NA_CEKANJU) || p.getStatusPica().equals(StatusPica.U_PRIPREMI)) {
					zaSpremanje.add(p);
				}
			}
		}
		}
		
		return new ResponseEntity<List<Pice>>(zaSpremanje, HttpStatus.OK);
		
		
		}
	
	
	@RequestMapping(value="/sankerPrihvatiPice/{idSankera}/{idPica}",method = RequestMethod.PUT)
	public ResponseEntity<List<Pice>> sankerPrihvatiPice (@PathVariable Long idPica){
		
		Pice picejedno=piceService.getPice(idPica);
		Set<GostNarudzbina> naNar=picejedno.getNaNarudzbinama();
		
	
		
		for(GostNarudzbina n:naNar) {
			if(n.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
				for(Pice p:n.getPicaNaNarudzbini()) {
					if(p.getId()==idPica) {
						p.setStatusPica(StatusPica.U_PRIPREMI);
						p.setId(idPica);
						piceService.sacuvaj(p);
						n.setId(n.getId());
						narudzbinaService.save(n);
					}
				}
			}
		}
		
		List<GostNarudzbina> sveNar = narudzbinaService.getAllNarudzbina();
		List<Pice> zaSpremanje=new ArrayList<Pice>();
		
		for(GostNarudzbina nar:sveNar) {
			
		if(nar.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
			for(Pice p:nar.getPicaNaNarudzbini()){
				if(p.getStatusPica().equals(StatusPica.NA_CEKANJU) || p.getStatusPica().equals(StatusPica.U_PRIPREMI)) {
					zaSpremanje.add(p);
				}
			}
		}
		}
		
		return new ResponseEntity<List<Pice>>(zaSpremanje, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/sankerSpremioPice/{idPica}",method = RequestMethod.PUT)
	public ResponseEntity<List<Pice>> sankerSpremioPice(@PathVariable Long idPica){
		
		Pice picejedno=piceService.getPice(idPica);
		Set<GostNarudzbina> naNar=picejedno.getNaNarudzbinama();
		
	
		
		for(GostNarudzbina n:naNar) {
			if(n.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
				for(Pice p:n.getPicaNaNarudzbini()) {
					if(p.getId()==idPica) {
						p.setStatusPica(StatusPica.SPREMLJENO);
						p.setId(idPica);
						piceService.sacuvaj(p);
						n.setId(n.getId());
						narudzbinaService.save(n);
					}
				}
			}
		}
		
		List<GostNarudzbina> sveNar = narudzbinaService.getAllNarudzbina();
		List<Pice> zaSpremanje=new ArrayList<Pice>();
		
		for(GostNarudzbina nar:sveNar) {
			
		if(nar.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
			for(Pice p:nar.getPicaNaNarudzbini()){
				if(p.getStatusPica().equals(StatusPica.NA_CEKANJU) || p.getStatusPica().equals(StatusPica.U_PRIPREMI)) {
					zaSpremanje.add(p);
				}
			}
		}
		}
		
		return new ResponseEntity<List<Pice>>(zaSpremanje, HttpStatus.OK);
	}
	
	@RequestMapping(value="/prikaziSveNarudzbine/{idKonobara}",method = RequestMethod.GET)
	public ResponseEntity<List<GostNarudzbina>> prikaziSveNarudzbine(@PathVariable Long idKonobara){
	
		//Radnik konobar=radnikService.getRadnik(idKonobara);
		List<GostNarudzbina> sve=narudzbinaService.getAllNarudzbina();
		List<GostNarudzbina> narudzbineKonobara=new ArrayList<>();
		for(GostNarudzbina nar:sve) {
			if(nar.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
			if(nar.getRadnik().getId()==idKonobara) {
				narudzbineKonobara.add(nar);
			}
		}
		
		}
		
		return new ResponseEntity<List<GostNarudzbina>>(narudzbineKonobara, HttpStatus.OK);
		
		
		
	
	}
	
	
	
	
	@RequestMapping(value="/uzmiNarudzbinu/{idNar}",method = RequestMethod.GET)
	public ResponseEntity<GostNarudzbina> izmeniNarudzbinu( @PathVariable Long idNar){
	
		GostNarudzbina nar=narudzbinaService.getNarudzbina(idNar);
		return new ResponseEntity<GostNarudzbina>(nar, HttpStatus.OK);
		
	
	}
	
	@RequestMapping(value="/zavrsiNarudzbinu/{idNar}/{idKonobara}",method = RequestMethod.GET)
	public ResponseEntity<List<GostNarudzbina>> zavrsiNarudzbinu( @PathVariable Long idNar, @PathVariable Long idKonobara){
	
		GostNarudzbina nar=narudzbinaService.getNarudzbina(idNar);
		nar.setStatusNarudzbine(StatusNarudzbine.GOTOVA);
		nar.setId(idNar);
		narudzbinaService.save(nar);
		
		List<GostNarudzbina> sve=narudzbinaService.getAllNarudzbina();
		List<GostNarudzbina> narudzbineKonobara=new ArrayList<>();
		for(GostNarudzbina jedna:sve) {
			if(jedna.getStatusNarudzbine().equals(StatusNarudzbine.U_TOKU)) {
			if(jedna.getRadnik().getId()==idKonobara) {
				narudzbineKonobara.add(jedna);
			}
		}
		
		}
		
		return new ResponseEntity<List<GostNarudzbina>>(narudzbineKonobara, HttpStatus.OK);
		
		
		
		
	
	}
	
	
	
/*	@RequestMapping(value="/dodajuNarudzbinu/{id}",method = RequestMethod.PUT)
	public ResponseEntity<GostNarudzbina> dodajUNarudzbinu(@PathVariable Long idNarudzbine, @RequestBody Jelo jelo){
		GostNarudzbina narudzbinaUKojuSeDodaje=narudzbinaService.getNarudzbina(idNarudzbine);
		
		DateTime doKada=new DateTime(narudzbinaUKojuSeDodaje.getRezervacija().getDatumVreme().getTime()+narudzbinaUKojuSeDodaje.getRezervacija().getVremeTrajanja());
		
		if(Minutes.minutesBetween(doKada, new DateTime())
	            .isGreaterThan(Minutes.minutes(10))) {
		
		narudzbinaUKojuSeDodaje.getJelaNaNarudzbini().add(jelo);
		return new ResponseEntity<GostNarudzbina>(narudzbinaUKojuSeDodaje, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	*/
	
}
