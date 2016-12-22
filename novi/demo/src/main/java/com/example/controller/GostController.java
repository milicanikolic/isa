package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Gost;
import com.example.service.GostService;

@Controller
@RequestMapping("/gost")
public class GostController {
	
	@Autowired
	private GostService gostService;
	
	@GetMapping("/login")
	public ModelAndView getLoggedUser(Long id) {
		Gost gost=gostService.getGost(id);
		
		return new ModelAndView("index.html", "gost" ,gost);
	}
	
}
