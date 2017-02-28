package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.example.enumeracije.StatusZahteva;
import com.example.enumeracije.VrsteKorisnika;
import com.example.model.Admin;
import com.example.model.Gost;
import com.example.model.Korisnik;
import com.example.model.Radnik;
import com.example.model.Kuvar.vrstaKuvara;
import com.example.model.MenadzerRestorana;
import com.example.model.Prijateljstva;
import com.example.service.AdminService;
import com.example.service.GostService;
import com.example.service.KorisnikService;
import com.example.service.PrijateljstvaService;


@Controller
@RequestMapping("/gost") 
public class GostController {
 
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
}