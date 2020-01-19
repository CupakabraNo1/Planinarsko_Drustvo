package com.planinarsko_drustvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;

public interface KorisnikRepo extends JpaRepository<Korisnik, Integer> {
	
	public Korisnik findByKorisnickoIme(String korisnickoIme);
}

