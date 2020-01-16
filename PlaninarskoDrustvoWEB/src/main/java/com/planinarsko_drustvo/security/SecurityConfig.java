package com.planinarsko_drustvo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier("customUserDetail")
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
	   http.authorizeRequests().
	   antMatchers("/","/loginPage").permitAll().
	   antMatchers("/admin/**").hasRole("MANAGER").
	   antMatchers("/users/**").hasAnyRole("EMPLOYEE","MANAGER").
	   and().formLogin().
	   loginPage("/pages/login.jsp?loged=false").
	   loginProcessingUrl("/login").
	   defaultSuccessUrl("/pages/home.jsp")
	   .failureForwardUrl("/pages/login?loged=true").and().
	   logout().invalidateHttpSession(true)
	   .logoutSuccessUrl("/pages/login.jsp?loged=false")
	   .and()
	   .exceptionHandling()
	   .accessDeniedPage("/pages/403.jsp")
	   .accessDeniedPage("/pages/access_denied.jsp").and().csrf().
	   and().rememberMe();
//	   and().csrf().disable();
	}



	

	
	

}
