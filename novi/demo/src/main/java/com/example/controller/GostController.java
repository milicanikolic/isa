package com.example.controller;

import java.util.List;

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



import com.example.model.Gost;
import com.example.service.GostService;

@Controller
@RequestMapping("/gost") 
public class GostController {
	
	@Autowired
	private GostService gostService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	 private JavaMailSender mailSender;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Gost>>  uzmiSveGoste(){
		List<Gost>gosti=  gostService.getAllGost();
		return new ResponseEntity<List<Gost>>(gosti,HttpStatus.OK);
		
	}
	/*public  getLoggedUser(HttpServletResponse httpServletResponse, @ModelAttribute("korisnickoIme") String kIme, @ModelAttribute("sifra") String sifra) throws IOException {

		logger.info("usao");
		httpServletResponse.sendRedirect("/profilRestoran.html");
		List<Gost> sviGosti=gostService.getAllGost();
		Gost gost=null;
		boolean dobar=false;
		
		for(Gost g:sviGosti){
			String username=g.getKorisnickoIme();
			String password=g.getSifra();
		
			if(kIme.equals(username) && password.equals(sifra)){
				dobar=true;
				gost=g;
			}
			
			
		}
		if(dobar){
			
			return new ModelAndView("/pocetna.html", "gost" ,gost);
		}
		return null;
	}*/
	@RequestMapping(value="/registruj",method = RequestMethod.POST)
	public ResponseEntity<Gost> registruj(@RequestBody Gost gost){
		
		List<Gost> sviGosti=gostService.getAllGost();
		boolean ispravno=true;
		for(Gost g: sviGosti){
			if(g.getEmail().equals(gost.getEmail()) || g.getKorisnickoIme().equals(gost.getKorisnickoIme())){
				ispravno=false;
				break;
			}
		}
		
		if(ispravno){
		
		Gost sacuvan=gostService.save(gost);
		
		SimpleMailMessage mail = new SimpleMailMessage();
		 mail.setTo(gost.getEmail());
		 mail.setFrom("restoranisa0@gmail.com");
		 mail.setSubject("Activation message");
		 mail.setText("http://localhost:8083/gost/aktiviraj/"+gost.getId());
		 
		   
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
	
	
	
	 @RequestMapping(value="/login",method = RequestMethod.POST)
	  public ResponseEntity<Gost> login(@RequestBody Gost gost){
	   
	   String kIme=gost.getKorisnickoIme();
	   List<Gost> gosti= gostService.getAllGost();
	   
	   
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
		return null;
	   
	   
	   
	  
	   
	   
	/*	logger.info("usaoLogin");
	   Gost logovan=gostService.findByKorisnickoIme(kIme);
	   if(logovan!=null){
	    if(logovan.getSifra().equals(gost.getSifra())){
	     return new ResponseEntity<Gost>(logovan,HttpStatus.CREATED);
	    }else{//TREBA PROMENITI
	     return new ResponseEntity<Gost>(logovan,HttpStatus.BAD_REQUEST);
	    }
	   }else{//TREBA PROMENITI
	    return new ResponseEntity<Gost>(logovan,HttpStatus.BAD_REQUEST);
	   }
	   */
	  
	  }
}
