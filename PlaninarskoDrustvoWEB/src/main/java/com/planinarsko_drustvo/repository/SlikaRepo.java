package com.planinarsko_drustvo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Slika;
import model.Znamenitost;

public interface SlikaRepo extends JpaRepository<Slika, Integer> {

	
	public List<Slika> findByZnamenitost(Znamenitost z);
}
