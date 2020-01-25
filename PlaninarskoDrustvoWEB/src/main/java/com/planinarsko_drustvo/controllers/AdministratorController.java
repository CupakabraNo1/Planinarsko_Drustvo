package com.planinarsko_drustvo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planinarsko_drustvo.repository.ClanarinaRepo;
import com.planinarsko_drustvo.repository.KorisnikRepo;
import com.planinarsko_drustvo.repository.UlogaRepo;
import com.planinarsko_drustvo.security.GeneratePassword;

import model.Clanarina;
import model.Korisnik;
import model.Uloga;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	KorisnikRepo kr;
	
	@Autowired
	ClanarinaRepo cr;
	
	@Autowired
	GeneratePassword gp;
	
	@Autowired
	UlogaRepo ur;
	
	@GetMapping("/clanstva")
	public String clanstva() {
		List<Korisnik> korisnici = kr.findAll();
		korisnici.stream().filter(x->x.getUloga().getIdUloga() == 2).collect(Collectors.toList());
		List<Uloga> uloge = ur.findAll();
		request.getSession().setAttribute("korisnici", korisnici);
		request.getSession().setAttribute("uloge", uloge);
		return "clanstva";
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatter.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormatter, true));
	}
	
	@PostMapping("/produzi")
	public String produzi(String id, Date od, Date do_) {
		
		Korisnik k = kr.findById(Integer.parseInt(id)).get();
		
		Clanarina c = cr.findByKorisnik(k);
		c.setOd(od);
		c.setDo_(do_);
		
		cr.save(c);
		
		return clanstva();
	}
	
	@PostMapping("/uclaniNovog")
	public String uclaniNovog(String ime, String prezime, String korisnicko_ime, String lozinka,String uloga,  Date od, Date do_) {
		
		Clanarina c = new Clanarina();
		c.setOd(od);
		c.setDo_(do_);
		
		c=cr.save(c);
		
		Korisnik k = new Korisnik();
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setKorisnickoIme(korisnicko_ime);
		k.setLozinka(gp.napraviLozinku(lozinka));
		Uloga u = ur.findById(Integer.parseInt(uloga)).get();
		k.setUloga(u);
		k.setClanarina(c);
		
		kr.save(k);
		return "clanstva";
	}
	
	@GetMapping("/statistike")
	public String statistike() {
		
		return "statistike";
	}
}
