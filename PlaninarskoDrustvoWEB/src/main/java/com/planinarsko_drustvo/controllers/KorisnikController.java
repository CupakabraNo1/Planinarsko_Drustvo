package com.planinarsko_drustvo.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planinarsko_drustvo.repository.DomRepo;
import com.planinarsko_drustvo.repository.PlaninaRepo;
import com.planinarsko_drustvo.repository.PosetaRepo;
import com.planinarsko_drustvo.repository.RezervacijaRepo;
import com.planinarsko_drustvo.repository.SlikaRepo;
import com.planinarsko_drustvo.repository.StazaRepo;
import com.planinarsko_drustvo.repository.TerminRepo;
import com.planinarsko_drustvo.repository.ZnamenitostRepo;

import model.Dom;
import model.Korisnik;
import model.Planina;
import model.Planinarska_staza;
import model.Poseta;
import model.Rezervacija;
import model.Slika;
import model.Termin_znamenitost;
import model.Znamenitost;

@Controller
@RequestMapping("/korisnik")
public class KorisnikController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	PlaninaRepo pr;

	@Autowired
	DomRepo dr;

	@Autowired
	RezervacijaRepo rr;

	@Autowired
	StazaRepo sr;

	@Autowired
	ZnamenitostRepo zr;

	@Autowired
	SlikaRepo slr;

	@Autowired
	PosetaRepo posr;

	@Autowired
	TerminRepo trzr;

	@GetMapping("/rezervisi")
	public String otvoriRezervacije() {
		@SuppressWarnings("unchecked")
		List<Planina> planine = (List<Planina>)request.getSession().getAttribute("planine");
		if(planine == null)
			planine = pr.findAll();
		request.getSession().setAttribute("planine", planine);
		return "rezervacija";
	}

	@GetMapping("/domovi")
	public String nadjiDomoveNaPlanini(String planina) {
		Planina planinaSaID = pr.findById(Integer.parseInt(planina)).get();
		List<Dom> domovi = dr.findByPlanina(planinaSaID);
		request.getSession().setAttribute("domovi", domovi);
		request.getSession().setAttribute("planina", planinaSaID);
		return "rezervacija";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatter.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormatter, true));
	}

	@PostMapping("/napraviRezervaciju")
	public String napraviRezervaciju(String dom, Date datumPocetka, Date datumZavrsetka) {

		Dom d = dr.findById(Integer.parseInt(dom)).get();
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");

		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setDom(d);
		rezervacija.setOd(datumPocetka);
		rezervacija.setDo_(datumZavrsetka);
		rezervacija.setKorisnik(k);

		rr.save(rezervacija);
		request.getSession().setAttribute("rezervacija", rezervacija);
		return "rezervacija";
	}
	
	@GetMapping("/staze")
	public String staze() {
		@SuppressWarnings("unchecked")
		List<Planina> planine = (List<Planina>)request.getSession().getAttribute("planine");
		if(planine == null)
			planine = pr.findAll();
		request.getSession().setAttribute("planine", planine);
		return "staze";
	}
	
	@GetMapping("/pregledStaza")
	public String pregledStaza(String planina) {
		Planina p = pr.findById(Integer.parseInt(planina)).get();
		List<Planinarska_staza> staze = sr.findByPlanina(p);
		List<String> bs64 = new ArrayList<String>();
		for (Planinarska_staza ps : staze) {
			bs64.add(Base64.getEncoder().encodeToString(ps.getMapa()));
		}

		request.getSession().setAttribute("bs64", bs64);
		request.getSession().setAttribute("staze", staze);
		return "staze";
	}
	
	@GetMapping("/znamenitosti")
	public String otvoriZnamenitosti(String staza) {
		List<Planinarska_staza> staze = sr.findAll();
		request.getSession().setAttribute("staze", staze);
		return "znamenitosti";
	}
	
	@GetMapping("/pregledZnamenitosti")
	public String pregledZnamenitosti(String staza) {
		Planinarska_staza pStaza = sr.findById(Integer.parseInt(staza)).get();
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
		List<Znamenitost> znamenitosti = zr.findByPlaninarskaStaza(pStaza);
		List<List<String>> slike = new ArrayList<List<String>>();
		List<Integer> posetio = new ArrayList<Integer>();

		for (Znamenitost z : znamenitosti) {
			List<Slika> slikas = slr.findByZnamenitost(z);
			List<String> slikeU64 = new ArrayList<String>();
			List<Poseta> poseteZnamenitostiOdKorisnika = posr.nadjiPoKorisnikuIZnamenitosti(k.getIdKorisnik(),
					z.getIdZnamenitost());
			if (poseteZnamenitostiOdKorisnika.size() > 0)
				posetio.add(1);
			else
				posetio.add(0);
			slikas.stream().forEach(s -> slikeU64.add(Base64.getEncoder().encodeToString(s.getSlika())));
			slike.add(slikeU64);
		}

		request.getSession().setAttribute("staza", pStaza);
		request.getSession().setAttribute("znamenitosti", znamenitosti);
		request.getSession().setAttribute("slike", slike);
		request.getSession().setAttribute("posetio", posetio);
		return "znamenitosti";
	}

	@GetMapping("/poseti")
	public String poseti(String z) {
		Znamenitost znamenitost = zr.findById(Integer.parseInt(z)).get();
		List<Termin_znamenitost> termini = trzr.findByZnamenitost(znamenitost);
		for(Termin_znamenitost t:termini) {
			if(!t.isNeophodan()) {
				termini.remove(t);
			}
		}
		request.getSession().setAttribute("termini", termini);
		if(termini.size() == 0) {
			request.getSession().setAttribute("termini", null);
		}
		
		return "poseta";
	}

	@PostMapping("/posetaTermina")
	public String posetaTermina(String termin) {
		Termin_znamenitost tz = trzr.findById(Integer.parseInt(termin)).get();
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
		Znamenitost z = tz.getZnamenitost();
		Planina p = z.getPlaninarskaStaza().getPlanina();
		List<Dom> domovi = dr.findByPlanina(p);

		Rezervacija r = null;
		Date poc = tz.getPocetak();
		Date kr = tz.getKraj();
		for (Dom d : domovi) {
			List<Rezervacija> rezervacije = rr.nadjiRezervacijuZaKorisnika(k.getIdKorisnik(), d.getIdDom());
			if (rezervacije != null) {
				for (Rezervacija rez : rezervacije) {
					if (rez.getOd().before(poc) && rez.getOd().before(kr)) {
						r = rez;
						break;
					}
				}
			}
		}

		if (tz != null && r != null) {
			Poseta poseta = new Poseta();
			poseta.setRezervacija(r);
			poseta.setTerminZnamenitost(tz);
			poseta = posr.save(poseta);
			request.getSession().setAttribute("pos", poseta);
		}else {
			request.getSession().setAttribute("poseta", 1);
		}
		return "poseta";
	}
	
	@GetMapping
	public String komentari() {
		@SuppressWarnings("unchecked")
		List<Znamenitost> znamenitosti = (List<Znamenitost>)request.getSession().getAttribute("znamenitosti");
		if(znamenitosti == null)
			znamenitosti = zr.findAll();
		request.getSession().setAttribute("znamenitosti", znamenitosti);
		return "komentari";
	}
}
