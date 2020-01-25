package com.planinarsko_drustvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Clanarina;
import model.Korisnik;

public interface ClanarinaRepo extends JpaRepository<Clanarina, Integer>{
	
	public Clanarina findByKorisnik(Korisnik k);
}	
