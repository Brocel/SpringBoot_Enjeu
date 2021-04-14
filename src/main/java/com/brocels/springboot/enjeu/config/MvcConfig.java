package com.brocels.springboot.enjeu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/").setViewName("home.html");
		registry.addViewController("/home").setViewName("home.html");
		registry.addViewController("/login").setViewName("home_login.html");
		registry.addViewController("/enjeu_home").setViewName("enjeu_home.html");
	}

	
}
