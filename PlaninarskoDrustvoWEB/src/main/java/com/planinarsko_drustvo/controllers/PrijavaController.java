package com.planinarsko_drustvo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planinarsko_drustvo.repository.KorisnikRepo;
import com.planinarsko_drustvo.repository.UlogaRepo;
import com.planinarsko_drustvo.security.GeneratePassword;

import model.Korisnik;
import model.Uloga;

@Controller
public class PrijavaController {
	@Autowired
	KorisnikRepo kr;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	GeneratePassword gp;
	
	@Autowired
	UlogaRepo ur;
	
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
	
	/*registrovanje novog korisika na sistem*/
	@PostMapping("/registracija")
	public String registrujSe(String ime, String prezime, String korisnicko_ime, String lozinka) {
		Korisnik k = new Korisnik();
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setKorisnickoIme(korisnicko_ime);
		k.setLozinka(gp.napraviLozinku(lozinka));
		Uloga u = ur.findById(2).get();
		k.setUloga(u);
		
		kr.save(k);
		
		return logovanjeStranica();
	}
}
