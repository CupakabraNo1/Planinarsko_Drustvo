package com.planinarsko_drustvo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {
	
	@GetMapping("/clanstva")
	public String clanstva() {
		
		return "clanstva";
	}
	
	@GetMapping("/statistike")
	public String statistike() {
		
		return "statistike";
	}
}
