package com.example.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.DelegatePerTargetObjectIntroductionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.enumeracije.StatusNarudzbine;
import com.example.enumeracije.StatusRezervacije;
import com.example.enumeracije.StatusSto;
import com.example.enumeracije.StatusZahteva;
import com.example.enumeracije.VrsteKorisnika;
import com.example.model.Gost;
import com.example.model.GostNarudzbina;
import com.example.model.Jelo;
import com.example.model.Korisnik;
import com.example.model.Ponudjac;
import com.example.model.Prijateljstva;
import com.example.model.Radnik;
import com.example.model.Restoran;
import com.example.model.Rezervacija;
import com.example.model.Sto;
import com.example.service.AdminService;
import com.example.service.GostService;
import com.example.service.JeloService;
import com.example.service.KorisnikService;
import com.example.service.NarudzbinaService;
import com.example.service.PonudjacService;
import com.example.service.PrijateljstvaService;
import com.example.service.RadnikService;
import com.example.service.RestoranService;
import com.example.service.RezervacijaService;
import com.example.service.StoService;


@Controller
@RequestMapping("/gost") 
public class GostController {
 
	@Autowired
	private StoService stoService;
	
	@Autowired
	 private PonudjacService ponudjacService;	
@Autowired
private JeloService jeloService;

@Autowired
private RestoranService restoranService;
	
@Autowired
private RadnikService radnikService;

@Autowired
private NarudzbinaService narudzbinaService;
	
@Autowired
private RezervacijaService rezervacijaService;

@Autowired
private PrijateljstvaService prijateljstvaService;

 @Autowired
 private GostService gostService;
 private Logger logger = LoggerFactory.getLogger(this.getClass());
 
 @Autowired
  private JavaMailSender mailSender;
 
 @Autowired
 private AdminService adminService;
 

 
 @Autowired
 private KorisnikService korisnikService;
 
 @Autowired
  private HttpSession sesija;
 
 @RequestMapping(method = RequestMethod.GET)
 public ResponseEntity<List<Gost>>  uzmiSveGoste(){
  List<Gost>gosti=  gostService.getAllGost();
  return new ResponseEntity<List<Gost>>(gosti,HttpStatus.OK);
  
 }
 
 @RequestMapping(value="/registruj",method = RequestMethod.POST)
 public ResponseEntity<Gost> registruj(@RequestBody Gost gost){
	 gost.setVrstaKorisnika(VrsteKorisnika.GOST);
	 logger.info("backk");
	 logger.info(gost.getVrstaKorisnika().toString());
  List<Gost> sviGosti=gostService.getAllGost();
  List<Korisnik> sviKorisnici=korisnikService.getAllKorisnik();
  boolean ispravno=true;
  for(Korisnik k: sviKorisnici){
   if(k.getEmail().equals(gost.getEmail()) || k.getKorisnickoIme().equals(gost.getKorisnickoIme())){
    ispravno=false;
    break;
   }
  }
  if(ispravno){
  
  Gost sacuvan=gostService.save(gost);
  korisnikService.sacuvaj(gost);
  
  SimpleMailMessage mail = new SimpleMailMessage();
   mail.setTo(gost.getEmail());
   mail.setFrom("restoranisa0@gmail.com");
   mail.setSubject("Activation message");
   mail.setText("http://localhost:8088/gost/aktiviraj/"+gost.getId());
   
         try {
             mailSender.send(mail);
         } catch (MailException ex) {
             System.out.println(ex);
         }
  
  return new ResponseEntity<Gost>(sacuvan,HttpStatus.CREATED);
  }else{
   logger.info("VEC POSTOJI");
   return new ResponseEntity<Gost>(gost,HttpStatus.BAD_GATEWAY);
   
  }
 }
 
 @RequestMapping(value = "/aktiviraj/{id}",method = RequestMethod.GET)
 public void aktiviraj(@PathVariable Long id) throws Exception {
  Gost gostSaPotvrdom = gostService.getGost(id);
  gostSaPotvrdom.setAktiviran(true);
  logger.info("AKTIVIRAAAAAAAAAAAAAN");
  gostService.updateGost(gostSaPotvrdom, id);
  
 }
 
 @RequestMapping(value="/uzmiUlogovanog",method = RequestMethod.GET)
 public ResponseEntity<Korisnik> uzmiUlogovanog(){
	 logger.info("uzmi ulogovanog back");
	 Korisnik kor=(Korisnik) sesija.getAttribute("ulogovani");
	 return new ResponseEntity<Korisnik>(kor,HttpStatus.OK);
	 
 }
 
 
 @RequestMapping(value="/izmeniGosta/{idStarog}",method = RequestMethod.PUT)
 public ResponseEntity<Gost> izmeniGosta(@PathVariable Long idStarog, @RequestBody Gost gost){
 	 
   List<Korisnik> sviKorisnici=korisnikService.getAllKorisnik();
   Korisnik izmenjen=korisnikService.getKorisnik(idStarog);
   
   sviKorisnici.remove(izmenjen);
   
   boolean ispravno=true;
   for(Korisnik k: sviKorisnici){
    if(k.getEmail().equals(gost.getEmail()) || k.getKorisnickoIme().equals(gost.getKorisnickoIme())){
     ispravno=false;
     break;
    }
   }
   if(ispravno){
   gost.setId(idStarog);
   Gost sacuvan=gostService.save(gost);
   korisnikService.sacuvaj(gost);
   return new ResponseEntity<Gost>(sacuvan, HttpStatus.CREATED);
   }
   else{
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
   }

 }
 
  @RequestMapping(value="/login",method = RequestMethod.POST)
   public ResponseEntity<Korisnik> login(@RequestBody Korisnik korisnik){
    logger.info("usao u login");
   //ODMAH DODAJEMO PREDEFINISANOG ADMINA
 /* String ime= "Nina";
   String prezime="Manojlovic";
   String email="nina@gmail.com";
   String korisnickoIme="nina1";
   String sifra="nina";
   VrsteKorisnika vk=VrsteKorisnika.ADMIN;
   Long id=(long) 1;
   Admin admin=new Admin(ime, prezime, email, korisnickoIme, sifra,vk);
   admin.setId(id);
   adminService.sacuvaj(admin);
   korisnikService.sacuvaj(admin);*/
   //////////////////////////////
   
   Korisnik ulogovani=null;
   boolean okej=false;
   
   List<Korisnik> sviKorisnici= korisnikService.getAllKorisnik();
   for(Korisnik kor:sviKorisnici){
	   logger.info(kor.getEmail());
    if(kor.getKorisnickoIme().equals(korisnik.getKorisnickoIme()) && kor.getSifra().equals(korisnik.getSifra())){
    	logger.info("Ovaj kao ok:   "+kor.getEmail());
     okej=true;
     ulogovani=kor;
     break;
    }
   }
   if(okej){
	   logger.info("usao u okej"+ulogovani);
	   Gost gost=null;
	   boolean aktiviran=false;
	   if(ulogovani.getVrstaKorisnika().equals(VrsteKorisnika.GOST)){
		   gost=gostService.findByKorisnickoIme(ulogovani.getKorisnickoIme());
		   aktiviran=gost.isAktiviran();
		   if(aktiviran){
			   sesija.setAttribute("ulogovaniGost", gost);
		   }
		   else{
			   return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		   }
	   }
	   sesija.setAttribute("ulogovani",ulogovani );
    return new ResponseEntity<Korisnik>(ulogovani,HttpStatus.OK);
   }else{
    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
   }
   
   
   
   
 /* boolean radnik=false;
  boolean go=false;
  boolean menadzer=false;
  boolean ponudjac=false;
  
   
     String kIme=gost.getKorisnickoIme();
     List<Gost> gosti= gostService.getAllGost();
     List<Radnik> sviRadnici=radnikService.getAllRadnik();
     List<MenadzerRestorana> sviMenadzeri= menadzerService.getAllMenadzeri();
     
     //Proveravam da li se logovani nalazi u nekoj tabeli korisnika,
     //ako ne, onda takav ne postoji
     
     for(Gost gg: gosti){
      if(gost.getKorisnickoIme().equals(gg.getKorisnickoIme())){
       go=true;
       break;
      }
     }
     
     for(Radnik r:sviRadnici){
      if(gost.getKorisnickoIme().equals(r.getKorisnickoIme())){
       radnik=true;
       break;
      }
     }
     
     for(MenadzerRestorana men:sviMenadzeri){
      if()
     }
     
     //Ako ga nismo nasli ni u jednoj tabeli, ne postoji registrovan
     if(radnik==false && go==false){
      return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
     }
     else if(go){
    
     Gost gostt=null;
  boolean dobar=false;
  
  for(Gost g:gosti){
   String username=g.getKorisnickoIme();
   String password=g.getSifra();
  
   if(gost.getKorisnickoIme().equals(username) && password.equals(gost.getSifra())){
    dobar=true;
    gostt=g;
   } 
  }
  if(dobar){
   
   return new ResponseEntity<Gost>(gostt,HttpStatus.OK);
  }
  }else if(radnik){
   boolean dobar2=false;
   
   for(Radnik r:sviRadnici){
    if(gost.getKorisnickoIme().equals(r.getKorisnickoIme()) && gost.getSifra().equals(r.getSifra())){
     dobar2=true;
     break;
    }
   }
   
   if(dobar2){
    //////////radnik
    return new ResponseEntity<Gost>(gost,HttpStatus.OK);
   }
  }
  return null;
 
   */
   }
  @RequestMapping(value="/proveriNotifikacije/{idKorisnik}",method = RequestMethod.GET)
  public ResponseEntity<List<Gost>> notifikacije(@PathVariable Long idKorisnik){
  
  List<Prijateljstva> svaPrijateljstva=prijateljstvaService.getAllPrijateljstva();
  List<Gost> zahteviOd=new ArrayList<Gost>();
  if(!svaPrijateljstva.isEmpty()){
  
   for(Prijateljstva p:svaPrijateljstva){
    if(p.getPrimalac().getId()==idKorisnik && p.getStatus().equals(StatusZahteva.NA_CEKANJU)){
     Gost gost= gostService.getGost(p.getPosiljalac().getId());
     zahteviOd.add(gost);
    }
   }
   if(zahteviOd.isEmpty()){
    //nema zahteva za tog korisnika
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
   }else{
    return new ResponseEntity<List<Gost>>(zahteviOd, HttpStatus.OK);
   }
  }else{
   //tabela prijateljstva je prazna
   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }
   
  }
  
  
  @RequestMapping(value="/rezervisi/{idGosta}/{idRestorana}/{datum}/{vreme}/{vremeTrajanja}/{idStola}",method = RequestMethod.POST)
  public ResponseEntity<List<Rezervacija>> rezervisi(@PathVariable Long idGosta,@PathVariable Long idRestorana, @PathVariable String datum, @PathVariable String vreme, @PathVariable String vremeTrajanja, @PathVariable Long idStola) {
   
Rezervacija nova=new Rezervacija();

Gost gostR=gostService.getGost(idGosta);

nova.setGost(gostR);

Restoran resR=restoranService.getRestoran(idRestorana);

nova.setRestoran(resR);
   
//SimpleDateFormat zaVreme = new SimpleDateFormat("HH:mm");




SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
try {
 //long ms = zaVreme.parse(vreme).getTime();
 //Time sqlTime = new Time(ms);
 java.util.Date datRez = sdf.parse(datum);
    java.sql.Date sqlDate = new java.sql.Date(datRez.getTime());
    
    nova.setDatum(sqlDate);
    nova.setVreme(vreme);
} catch (ParseException e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
}
nova.setVremeTrajanja(Integer.parseInt(vremeTrajanja));  
Sto stoR=stoService.getSto(idStola);
nova.setRezervisanSto(stoR);
   nova.setStatusRez(StatusRezervacije.AKTIVNA);
rezervacijaService.sacuvaj(nova);
   
   List<Rezervacija> sve=rezervacijaService.getAllRezervacije();
   List<Rezervacija> sveGosta=new ArrayList<Rezervacija>();
   
   for(Rezervacija r:sve) {
    if(r.getGost().getId()==idGosta) {
     sveGosta.add(r);
    }
   }
   
   
   
   
   
   
   
   return new ResponseEntity<List<Rezervacija>>(sveGosta,HttpStatus.CREATED);

  }
  
  @RequestMapping(value="/otkaziRezervaciju",method = RequestMethod.POST)
  public ResponseEntity<Rezervacija> otkaziRezervaciju(@PathVariable Long idRez) {
   Rezervacija zaBrisanje=rezervacijaService.getRezervacija(idRez);
   
  if(Minutes.minutesBetween(new DateTime(zaBrisanje.getDatum()), new DateTime())
           .isGreaterThan(Minutes.minutes(30))) {
zaBrisanje.setStatusRez(StatusRezervacije.NEAKTIVNA);
   zaBrisanje.setId(idRez);
   rezervacijaService.sacuvaj(zaBrisanje);
   return new ResponseEntity<Rezervacija>(zaBrisanje,HttpStatus.OK);
  }
  else {
   return new ResponseEntity<>(null, 
     HttpStatus.BAD_REQUEST);
  }
  
  }
  
  
  @RequestMapping(value="/prikaziIstorijuPoseta/{idGosta}",method = RequestMethod.GET)
  public ResponseEntity<List<GostNarudzbina>> prikaziIstorijuPoseta(@PathVariable Long idGosta) {
   
   List<GostNarudzbina> sveNarudzbine=narudzbinaService.getAllNarudzbina();
   List<GostNarudzbina> njegoveProsle=new ArrayList<GostNarudzbina>();
   
   for(GostNarudzbina nar:sveNarudzbine) {
    if(nar.getStatusNarudzbine().equals(StatusNarudzbine.GOTOVA)) {
     if(nar.getGostNarucio().getId()==idGosta) {
      njegoveProsle.add(nar);
     }
    }
   }
   
   return new ResponseEntity<List<GostNarudzbina>>(njegoveProsle, HttpStatus.OK);
  
  }
  
  
  @RequestMapping(value="/oceniObrok/{idGosta}/{idNar}/{ocena}",method = RequestMethod.POST)
  public ResponseEntity<List<GostNarudzbina>> oceniObrok(@PathVariable Long idGosta, @PathVariable Long idNar, @PathVariable String ocena) {
   
   
   GostNarudzbina nar=narudzbinaService.getNarudzbina(idNar);
   nar.setOcenioObrok(true);
     Set<Jelo> naNar=nar.getJelaNaNarudzbini();
   
   for(Jelo j:naNar) {
   int brojOcenaStaro=j.getBrojOcenaJelo();
   int brojOcenaNovo=brojOcenaStaro+1;
   j.setBrojOcenaJelo(brojOcenaNovo);
   double ocenaStara= j.getOcena();
   double ocenaNova = (ocenaStara*brojOcenaStaro+Integer.parseInt(ocena))/brojOcenaNovo;
   j.setOcena(ocenaNova);
   j.setId(j.getId());
   jeloService.sacuvaj(j);
   }
   
   nar.setId(idNar);
   narudzbinaService.save(nar);
   
   List<GostNarudzbina> sveNarudzbine=narudzbinaService.getAllNarudzbina();
   List<GostNarudzbina> njegoveProsle=new ArrayList<GostNarudzbina>();
   
   for(GostNarudzbina narjed:sveNarudzbine) {
    if(narjed.getStatusNarudzbine().equals(StatusNarudzbine.GOTOVA)) {
     if(narjed.getGostNarucio().getId()==idGosta) {
      njegoveProsle.add(narjed);
     }
    }
   }
   
   return new ResponseEntity<List<GostNarudzbina>>(njegoveProsle, HttpStatus.OK);
   
  }
  
  @RequestMapping(value="/oceniRestoran/{idGosta}/{idNar}/{ocena}",method = RequestMethod.POST)
  public ResponseEntity<List<GostNarudzbina>> oceniRestoran(@PathVariable Long idGosta, @PathVariable Long idNar, @PathVariable String ocena) {
   
   
   GostNarudzbina nar=narudzbinaService.getNarudzbina(idNar);

   Restoran res=nar.getRadnik().getRestoran();
   
   nar.setOcenioRes(true);
   
   int brojOcenaStaro=res.getBrojOcenaRes();
   int brojOcenaNovo=brojOcenaStaro+1;
   res.setBrojOcenaRes(brojOcenaNovo);
   double ocenaStara= res.getOcena();
   double ocenaNova = (ocenaStara*brojOcenaStaro+Integer.parseInt(ocena))/brojOcenaNovo;
   res.setOcena(ocenaNova);
   res.setId(res.getId());
   restoranService.sacuvaj(res);
   
   nar.setId(idNar);
    narudzbinaService.save(nar);
   
   
   List<GostNarudzbina> sveNarudzbine=narudzbinaService.getAllNarudzbina();
   List<GostNarudzbina> njegoveProsle=new ArrayList<GostNarudzbina>();
   
   for(GostNarudzbina narjed:sveNarudzbine) {
    if(narjed.getStatusNarudzbine().equals(StatusNarudzbine.GOTOVA)) {
     if(narjed.getGostNarucio().getId()==idGosta) {
      njegoveProsle.add(narjed);
     }
    }
   }
   
   return new ResponseEntity<List<GostNarudzbina>>(njegoveProsle, HttpStatus.OK);
   
  }
  
  @RequestMapping(value="/oceniUslugu/{idGosta}/{idNar}/{ocena}",method = RequestMethod.POST)
  public ResponseEntity<List<GostNarudzbina>> oceniUslugu(@PathVariable Long idGosta, @PathVariable Long idNar, @PathVariable String ocena) {
   
   
   GostNarudzbina nar=narudzbinaService.getNarudzbina(idNar);
   
   nar.setOcenioUslugu(true);

   Radnik konobar=nar.getRadnik();
   
   int brojOcenaStaro=konobar.getBrojOcenaKonobar();
   int brojOcenaNovo=brojOcenaStaro+1;
   konobar.setBrojOcenaKonobar(brojOcenaNovo);
   double ocenaStara= konobar.getOcenaKonobara();
   double ocenaNova = (ocenaStara*brojOcenaStaro+Integer.parseInt(ocena))/brojOcenaNovo;
   konobar.setOcenaKonobara(ocenaNova);
   konobar.setId(konobar.getId());
   radnikService.save(konobar);
   
   nar.setId(idNar);
    narudzbinaService.save(nar);
   
   List<GostNarudzbina> sveNarudzbine=narudzbinaService.getAllNarudzbina();
   List<GostNarudzbina> njegoveProsle=new ArrayList<GostNarudzbina>();
   
   for(GostNarudzbina narjed:sveNarudzbine) {
    if(narjed.getStatusNarudzbine().equals(StatusNarudzbine.GOTOVA)) {
     if(narjed.getGostNarucio().getId()==idGosta) {
      njegoveProsle.add(narjed);
     }
    }
   }
   
   return new ResponseEntity<List<GostNarudzbina>>(njegoveProsle, HttpStatus.OK);
   
  }
  
  
  @RequestMapping(value="/resetujSifru/{idKorisnik}/{sifra}",method = RequestMethod.PUT)
  public ResponseEntity<Korisnik> resetujSifru(@PathVariable Long idKorisnik, @PathVariable String sifra){
  
   Korisnik logujeSe=korisnikService.getKorisnik(idKorisnik);
   if(logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.PONUDJAC) || logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.KONOBAR) || logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.SANKER) || logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_KUVANA_JELA) || logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_PECENA_JELA) || logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_SALATE)){
    
  logujeSe.setId(idKorisnik);
  logujeSe.setSifra(sifra);
  korisnikService.sacuvaj(logujeSe);
  
  if(logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.PONUDJAC)){
   Ponudjac pon=ponudjacService.getPonudjac(idKorisnik);
   pon.setId(idKorisnik);
   pon.setSifra(sifra);
   pon.setUlogovanPrviPut(false);
  }else if(logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.KONOBAR) || logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.SANKER) || logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_KUVANA_JELA) || logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_PECENA_JELA) || logujeSe.getVrstaKorisnika().equals(VrsteKorisnika.KUVAR_ZA_SALATE)){
      Radnik rad= radnikService.getRadnik(idKorisnik);
      rad.setId(idKorisnik);
      rad.setSifra(sifra);
      rad.setUlogovanPrviPut(false);
      
  }
  
  return new ResponseEntity<Korisnik>(logujeSe, HttpStatus.OK);
   
  }
   return null;
  }
  
  @RequestMapping(value="/prikazStolova/{idRestorana}/{datum}/{vreme}/{vremeTrajanja}",method = RequestMethod.GET)
  public ResponseEntity<List<Sto>> prikazStolova(@PathVariable Long idRestorana,@PathVariable String datum,@PathVariable String vreme,@PathVariable String vremeTrajanja){
   
	  logger.info(datum);
	  logger.info(vreme);
	  logger.info(vremeTrajanja);
	  
   List<Sto> sviStolovi=stoService.findAll();
   List<Sto> stoloviRestorana=new ArrayList<Sto>();
   List<Rezervacija> sveRezervacije = rezervacijaService.getAllRezervacije();
   List<Rezervacija> rezervacijeUTomRestoranu=new ArrayList<Rezervacija>();
   List<Sto> konacna=new ArrayList<Sto>();
   for(Sto jedan:sviStolovi) {
    if(jedan.getRestoran().getId()==idRestorana) {
     stoloviRestorana.add(jedan);
    }
   }
   
   for(Rezervacija rez:sveRezervacije) {
    if(rez.getRestoran().getId()==idRestorana) {
     rezervacijeUTomRestoranu.add(rez);
    }
   }
   
   List<Rezervacija> togDana=new ArrayList<Rezervacija>();
   
   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
   //SimpleDateFormat zaVreme = new SimpleDateFormat("HH:mm");
   java.sql.Date sqlDate;
   //java.sql.Time sqlTime;
   try {
   logger.info("uso u traj");
   java.util.Date datRez = sdf.parse(datum);
      sqlDate = new java.sql.Date(datRez.getTime());
      //long ms = zaVreme.parse(vreme).getTime();
   //sqlTime = new Time(ms);
   
   }
   catch(ParseException e) {
	   logger.info("uso u kec");
    e.printStackTrace();
    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
   }
   
   
   for(Sto s:stoloviRestorana) {
   for(Rezervacija rez:rezervacijeUTomRestoranu) {
    if(rez.getDatum().compareTo(sqlDate)==0){
    logger.info("isti datumi");
     String vreme1S=rez.getVreme();
     String[] deloviVremena1=vreme1S.split(":");
     
    
     String[] deloviVremena2=vreme.split(":");
     
     int pocetakMoje=Integer.parseInt(deloviVremena2[0]);
     int krajnjeMoje=Integer.parseInt(deloviVremena2[0])+Integer.parseInt(vremeTrajanja);
     int pocetakPostojeca=Integer.parseInt(deloviVremena1[0]);
     int krajnjePostojeca=Integer.parseInt(deloviVremena1[0])+rez.getVremeTrajanja();
     
     
    

logger.info("pocetakTvog" + pocetakMoje);
logger.info("krajTvog" + krajnjeMoje);
logger.info("pocetakPostojece" + pocetakPostojeca);

logger.info("krajPostojece" + krajnjePostojeca);
         
	if ((pocetakMoje <= krajnjePostojeca) && (pocetakPostojeca <= krajnjeMoje))
         {
        	 logger.info("isti vreme");
          if(rez.getRezervisanSto().getId()==s.getId()) {
        	  logger.info("isti sto");
           s.setSlobodan(false);
           s.setStatus(StatusSto.ZAUZET);
          }
         }
           
     }
    
   }
   konacna.add(s);
 }
   
  
   return new ResponseEntity<List<Sto>>(konacna, HttpStatus.OK);
  }
  
  @RequestMapping(value="/uzmiRezervacijeKorisnik/{idGosta}",method = RequestMethod.GET)
  public ResponseEntity<List<Rezervacija>> uzmiRezervacijeKorisnik(@PathVariable Long idGosta) {
  
   List<Rezervacija> sve=rezervacijaService.getAllRezervacije();
    List<Rezervacija> sveGosta=new ArrayList<Rezervacija>();
    
    for(Rezervacija r:sve) {
     if(r.getGost().getId()==idGosta) {
      sveGosta.add(r);
     }
    }
  
    return new ResponseEntity<List<Rezervacija>>(sveGosta,HttpStatus.CREATED);    
  }
  
  @RequestMapping(value="/logOut",method=RequestMethod.GET)
  public void logOut(){
	  sesija.invalidate();
	  logger.info("izlogovao");
  }
}