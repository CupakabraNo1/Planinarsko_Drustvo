package com.planinarsko_drustvo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrijavaController {
	
	//svi dolazni se salju na logovanje
	@GetMapping("/logovanje")
	public String logovanjeStranica() {
		return "logovanje";
	}
	
	//svi dolazni na root se salju na logovanje
	@GetMapping("/")
	public String logovanjeStranicaSaRoota() {
		return "logovanje";
	}
	
	//ako je validacija uspesna salje se na indeks sa potrebnim podacima
	@GetMapping("/ulogovan")
	public String ulogovan() {
		/*prikupljanje potrebnih podataka za indeks stranicu*/
		return "index";
	}
}
