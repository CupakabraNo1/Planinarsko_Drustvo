package com.planinarsko_drustvo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Termin_znamenitost;
import model.Znamenitost;

public interface TerminRepo extends JpaRepository<Termin_znamenitost, Integer>{
	
	public List<Termin_znamenitost> findByZnamenitost(Znamenitost z);

}
