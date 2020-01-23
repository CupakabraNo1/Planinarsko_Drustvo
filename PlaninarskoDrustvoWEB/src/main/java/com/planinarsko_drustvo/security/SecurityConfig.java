package com.planinarsko_drustvo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService uds;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uds);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//autorizacija korisnika na osnovu njihove uloge u bazi
		http.authorizeRequests(authorise -> {
			authorise.antMatchers("/", "/logovanje").permitAll();
			authorise.antMatchers("/administrator/**").hasRole("administrator");
			authorise.antMatchers("/korisnik/**").hasAnyRole("korisnik", "administrator");
		});
		
		//logovanje na odgovarajucu stranicu
		http.formLogin(login -> {
			login.loginPage("/pages/logovanje.jsp");
			login.loginProcessingUrl("/login");
			login.defaultSuccessUrl("/ulogovan");
			login.failureForwardUrl("/pages/neuspesno_logovanje.jsp");
		});
		
		//brisanje podataka iz sesije prilikom odjavljivanja
		http.logout(logout -> {
			logout.invalidateHttpSession(true);
			logout.logoutSuccessUrl("/");
		});
		
		//greske koje se javljaju
		http.exceptionHandling(exception -> {
			exception.accessDeniedPage("/pages/neautorizovan_pristup.jsp");
		});
		
		//pamti podatke u kuki
		http.rememberMe();
		
		//csrf token za forme
		http.csrf();
	}

}
