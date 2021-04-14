package com.brocels.springboot.enjeu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
			.antMatchers("/", "/home","/home_createPlayerForm").permitAll()
			.antMatchers("/enjeu_home", "/enjeu_player", "/enjeu_playerlist").access("hasAnyAuthority('USERS','ADMIN')")
			.antMatchers("/css/**", "/**").permitAll()
			// CODER LES ROLES
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/enjeu_home", true)
			.permitAll()
			.and()
		.logout()
			.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("user")
			.password(passwordEncoder().encode("password")).authorities("USER");
	}
}
