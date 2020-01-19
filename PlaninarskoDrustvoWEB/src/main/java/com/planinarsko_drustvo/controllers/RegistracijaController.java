package com.planinarsko_drustvo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registracija")
public class RegistracijaController {
	
	@Autowired
	HttpServletRequest request;
	
	@PostMapping("/registruj")
	public String registrujSe(String ime, String prezime, String korisnicko_ime, String lozinka) {
		
		
		return "index";
	}

}
