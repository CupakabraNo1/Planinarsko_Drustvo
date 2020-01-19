package com.planinarsko_drustvo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrijavaController {
	
	@Autowired
	HttpServletRequest request;
	
	//svi dolazni se salju na logovanje
	@GetMapping("/logovanje")
	public String logovanjeStranica() {
		request.getSession().removeAttribute("reg");
		request.getSession().setAttribute("loguj", 1);
		return "logovanje";
	}
	
	//svi dolazni na root se salju na logovanje
	@GetMapping("/")
	public String logovanjeStranicaSaRoota() {
		request.getSession().removeAttribute("reg");
		request.getSession().setAttribute("loguj", 1);
		return "logovanje";
	}
	
	//ako je validacija uspesna salje se na indeks sa potrebnim podacima
	@GetMapping("/ulogovan")
	public String ulogovan() {
		/*prikupljanje potrebnih podataka za indeks stranicu*/
		return "index";
	}
	
	@GetMapping("/registruj")
	public String registruj() {
		/*prikupljanje potrebnih podataka za indeks stranicu*/
		request.getSession().removeAttribute("loguj");
		request.getSession().setAttribute("reg", 1);
		return "logovanje";
	}
}
