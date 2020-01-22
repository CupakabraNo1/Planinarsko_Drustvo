package com.planinarsko_drustvo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Planinarska_staza;
import model.Znamenitost;

public interface ZnamenitostRepo extends JpaRepository<Znamenitost, Integer> {
	
	public List<Znamenitost> findByPlaninarskaStaza(Planinarska_staza staza);
}
