package com.planinarsko_drustvo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import model.Korisnik;



public class CustomUserDetail implements UserDetails {

	/**
	 * 
	 */
	
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Korisnik k;
	
	public Korisnik getU() {
		return k;
	}
	
	public void setU(Korisnik k) {
		this.k = k;
	}
	
	public CustomUserDetail(Korisnik user) {
		this.k = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(k.getUloga().getNaziv()));
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return k.getLozinka();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return k.getKorisnickoIme();
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


}
