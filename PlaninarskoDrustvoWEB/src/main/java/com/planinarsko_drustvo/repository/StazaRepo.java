package com.planinarsko_drustvo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Planina;
import model.Planinarska_staza;

public interface StazaRepo extends JpaRepository<Planinarska_staza, Integer> {
	
	public List<Planinarska_staza> findByPlanina(Planina planina);
}
