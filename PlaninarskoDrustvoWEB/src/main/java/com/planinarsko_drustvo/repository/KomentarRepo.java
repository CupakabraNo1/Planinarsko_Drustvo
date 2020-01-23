package com.planinarsko_drustvo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Komentar;

public interface KomentarRepo extends JpaRepository<Komentar, Integer>{
	
	@Query("select k from Komentar k where k.poseta.terminZnamenitost.znamenitost.idZnamenitost = :idZ")
	public List<Komentar> nadjiKomentareZaZnamenitost(@Param("idZ") Integer idZ);
}
