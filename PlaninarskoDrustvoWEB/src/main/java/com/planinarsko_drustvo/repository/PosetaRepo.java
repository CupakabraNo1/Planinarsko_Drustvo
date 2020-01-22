package com.planinarsko_drustvo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Poseta;

public interface PosetaRepo extends JpaRepository<Poseta, Integer>{
	
	
	@Query("select p from Poseta p "
		 + "where p.rezervacija.korisnik.idKorisnik = :idKorisnik and "
		 + "p.terminZnamenitost.znamenitost.idZnamenitost = :idZnamenitost")
	
	public List<Poseta> nadjiPoKorisnikuIZnamenitosti(@Param("idKorisnik") Integer idKorisnik, @Param("idZnamenitost") Integer idZnamenitost);
}
