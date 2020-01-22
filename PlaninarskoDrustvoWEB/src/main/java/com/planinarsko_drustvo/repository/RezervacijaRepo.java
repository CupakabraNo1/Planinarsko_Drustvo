package com.planinarsko_drustvo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Rezervacija;

public interface RezervacijaRepo extends JpaRepository<Rezervacija, Integer> {
	
	@Query("select r from Rezervacija r where r.dom.idDom = :idD and r.korisnik.idKorisnik = :idK")
	public List<Rezervacija> nadjiRezervacijuZaKorisnika(@Param("idK") Integer idK, @Param("idD") Integer idD);

}
