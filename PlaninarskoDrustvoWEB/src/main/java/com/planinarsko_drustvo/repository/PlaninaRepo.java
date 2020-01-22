package com.planinarsko_drustvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Planina;

public interface PlaninaRepo extends JpaRepository<Planina, Integer> {
	
}
