package com.planinarsko_drustvo.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.planinarsko_drustvo.repository.KorisnikRepo;

import model.Korisnik;


@Service
public class UserDetailProvider implements UserDetailsService {
	
	@Autowired
	KorisnikRepo kr;
	
	@Autowired
	public HttpServletRequest request;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik k = kr.findByKorisnickoIme(username);
		UserDetails ud = null;
		if(k!=null)
			ud = new CustomUserDetail(k);
			request.setAttribute("korisnik", k);
		return ud;
	}
}
