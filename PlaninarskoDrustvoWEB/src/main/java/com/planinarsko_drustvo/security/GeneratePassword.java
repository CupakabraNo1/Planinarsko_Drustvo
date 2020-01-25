package com.planinarsko_drustvo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class GeneratePassword {
	
//	public static void main(String[] args) {
//		String password = "admin";
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		System.out.println(passwordEncoder.encode(password));
//	}
	
	public String napraviLozinku(String lozinka) {
		
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String encoded = pe.encode(lozinka);
		System.out.println(encoded);
		return encoded;
	}
}
