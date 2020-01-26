package com.planinarsko_drustvo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planinarsko_drustvo.repository.DomRepo;
import com.planinarsko_drustvo.repository.IzvestajRepo;
import com.planinarsko_drustvo.repository.KomentarRepo;
import com.planinarsko_drustvo.repository.PlaninaRepo;
import com.planinarsko_drustvo.repository.PosetaRepo;
import com.planinarsko_drustvo.repository.RezervacijaRepo;
import com.planinarsko_drustvo.repository.SlikaRepo;
import com.planinarsko_drustvo.repository.StazaRepo;
import com.planinarsko_drustvo.repository.TerminRepo;
import com.planinarsko_drustvo.repository.ZnamenitostRepo;

import model.Dom;
import model.Izvestaj;
import model.Komentar;
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
	
	@Autowired
	KomentarRepo kr;
	
	@Autowired
	IzvestajRepo ir;

	
	@GetMapping("/rezervisi")
	public String otvoriRezervacije() {
		request.getSession().removeAttribute("rezervacija");
		@SuppressWarnings("unchecked")
		List<Planina> planine = (List<Planina>)request.getSession().getAttribute("planine");
		if(planine == null)
			planine = pr.findAll();
		request.getSession().setAttribute("planine", planine);
		return "rezervacija";
	}

	@GetMapping("/domovi")
	public String nadjiDomoveNaPlanini(String planina) {
		request.getSession().removeAttribute("rezervacija");
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
		request.getSession().removeAttribute("rezervacija");

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
		request.getSession().removeAttribute("planine");
		request.getSession().removeAttribute("staze");
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
		request.getSession().removeAttribute("znamenitosti");
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
		
		
		List<Planinarska_staza> staze = sr.findAll();
		request.getSession().setAttribute("staze", staze);
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
		List<Termin_znamenitost> neopt = new ArrayList<Termin_znamenitost>();
		if(termini != null) {
			for(Termin_znamenitost t:termini) {
				if(t.getNeophodan() == 0) {
					neopt.add(t);
				}
			}
		}
		request.getSession().setAttribute("termini", termini);
		if(neopt == null || neopt.size() > 0) {
			request.getSession().setAttribute("termini", null);
			request.getSession().setAttribute("znamenitost", znamenitost);
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
		List<Rezervacija> rez = new ArrayList<Rezervacija>();
		for (Dom d : domovi) {
			List<Rezervacija> rezervacije = rr.nadjiRezervacijuZaKorisnika(k.getIdKorisnik(), d.getIdDom());
			rezervacije = rezervacije.stream().filter(x->x.getOd().before(poc)).filter(x->x.getDo_().after(poc)).collect(Collectors.toList());
			rez.addAll(rezervacije);
		}
		if(rez != null && rez.size() > 0) {
			r = rez.get(0);
		}
		if (r != null) {
			Poseta poseta = new Poseta();
			poseta.setRezervacija(r);
			poseta.setTerminZnamenitost(tz);
			poseta = posr.save(poseta);
			request.getSession().setAttribute("pos", poseta);
			request.getSession().removeAttribute("poseta");
		}else {
			request.getSession().setAttribute("poseta", 1);
			request.getSession().removeAttribute("pos");
		}
		return "poseta";
	}
	
	@PostMapping("/posetaVanTermina")
	public String posetaVanTermina(String znamenitost, Date pocetak, Date kraj) {
		
		Znamenitost z = zr.findById(Integer.parseInt(znamenitost)).get();
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
		
		Planina p = z.getPlaninarskaStaza().getPlanina();
		List<Dom> domovi = dr.findByPlanina(p);

		Rezervacija r = null;
		
		List<Rezervacija> rez = new ArrayList<Rezervacija>();
		for (Dom d : domovi) {
			List<Rezervacija> rezervacije = rr.nadjiRezervacijuZaKorisnika(k.getIdKorisnik(), d.getIdDom());
			rezervacije = rezervacije.stream().filter(x->x.getOd().before(pocetak)).filter(x->x.getDo_().after(pocetak)).collect(Collectors.toList());
			rez.addAll(rezervacije);
		}
		if(rez != null && rez.size() > 0) {
			r = rez.get(0);
		}
		if (r != null) {
			Poseta poseta = new Poseta();
			poseta.setRezervacija(r);
			
			Termin_znamenitost tz = new Termin_znamenitost();
			tz.setZnamenitost(z);
			tz.setPocetak(pocetak);
			tz.setKraj(kraj);
			tz.setNeophodan(0);
			trzr.save(tz);
			
			poseta.setTerminZnamenitost(tz);
			poseta = posr.save(poseta);
			request.getSession().setAttribute("pos", poseta);
			request.getSession().removeAttribute("poseta");
		}else {
			request.getSession().setAttribute("poseta", 1);
			request.getSession().removeAttribute("pos");
		}
		
		return "poseta";
	}
	
	@GetMapping("/komentari")
	public String komentari() {
		request.getSession().removeAttribute("znamenitosti");
		List<Znamenitost> znamenitosti = zr.findAll();
		request.getSession().setAttribute("znamenitosti", znamenitosti);
		return "komentari";
	}
	
	@GetMapping("/komentariZnamenitosti")
	public String komentariZnamenitosti(String znamenitost) {
		Znamenitost z = zr.findById(Integer.parseInt(znamenitost)).get();
		List<Komentar> komentariZaZ = kr.nadjiKomentareZaZnamenitost(z.getIdZnamenitost());
		Korisnik k = (Korisnik)request.getSession().getAttribute("korisnik");
		
		List<Poseta> posete = posr.nadjiPoKorisnikuIZnamenitosti(k.getIdKorisnik(), z.getIdZnamenitost());
		Poseta poseta = null;
		if(posete != null && posete.size() > 0)
			poseta = posete.get(0);
		
		request.getSession().setAttribute("poseta", poseta);
		request.getSession().setAttribute("znamenitost", z);
		request.getSession().setAttribute("komentari", komentariZaZ);
		return komentari();
	}

	@PostMapping("/komentarisi")
	public String komentarisi(String tekst) {
		
		Znamenitost z = (Znamenitost) request.getSession().getAttribute("znamenitost");
		Poseta poseta = (Poseta) request.getSession().getAttribute("poseta");
		
		
		Komentar kom = new Komentar();
		kom.setPoseta(poseta);
		kom.setTekst(tekst);
		
		kom = kr.save(kom);
		
		
		@SuppressWarnings("unchecked")
		List<Komentar> komentari = (List<Komentar>)request.getSession().getAttribute("komentari");
		komentari.add(kom);
		request.getSession().setAttribute("komentari", komentari);
		return "komentari";
	}
	
	@GetMapping("/izvestaji")
	public String izvestaji(String planina) {
		Korisnik k = (Korisnik)request.getSession().getAttribute("korisnik");
		List<Rezervacija> rezervacije = rr.nadjiRezervacijeZaPlaninu(Integer.parseInt(planina));
		rezervacije.stream().filter(x->x.getKorisnik().getIdKorisnik() == k.getIdKorisnik()).collect(Collectors.toList());
		Rezervacija r = null;
		if(rezervacije != null && rezervacije.size() > 0) {
			r = rezervacije.get(0);
			request.getSession().setAttribute("rezervacija", r);
		}
		
		return "izvestaji";
	}

	
	@PostMapping("/dodajIzvestaj")
    public String dodajIzvestaj(@RequestParam("slika") MultipartFile file, String rezervacija, String izvestaj
                                   ) {

        try {
        	 Izvestaj i = new Izvestaj();
             Rezervacija r = rr.findById(Integer.parseInt(rezervacija)).get();
             i.setRezervacija(r);
             i.setTekst(izvestaj);
             i = ir.save(i);
             
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Slika s = new Slika();
            s.setSlika(bytes);
            s.setIzvestaj(i);
            s = slr.save(s);
            
           
            
            request.getSession().setAttribute("izvestaj", i);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "izvestaji";
    }
	

}



