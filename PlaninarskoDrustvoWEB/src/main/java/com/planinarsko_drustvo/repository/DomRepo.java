package com.planinarsko_drustvo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Dom;
import model.Planina;

public interface DomRepo extends JpaRepository<Dom, Integer> {
	
	public List<Dom> findByPlanina(Planina p);
}
