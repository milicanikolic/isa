package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Gost;
import com.example.model.Konobar;
import com.example.service.GostService;

@Controller
@RequestMapping("/gost")
public class GostController {
	
	@Autowired
	private GostService gostService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/login")
	public ModelAndView getLoggedUser(HttpServletResponse httpServletResponse, @ModelAttribute("korisnickoIme") String kIme, @ModelAttribute("sifra") String sifra) throws IOException {

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
	}
	@RequestMapping(value="/pomeri")
	public void pomeri(HttpServletResponse httpServletResponse) throws IOException{
		logger.info("usao");
		httpServletResponse.sendRedirect("/Re");
	}
	
}
