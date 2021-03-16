package com.brocels.springboot.enjeu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.brocels.springboot.enjeu.service.EnjeuMainService;

@Controller
public class EnjeuMainController {

	private EnjeuMainService enjeuMainService;
	
	private final Logger logger = LoggerFactory.getLogger(EnjeuMainController.class);

	@Autowired
	public EnjeuMainController(EnjeuMainService enjeuMainService) {
		super();
		this.enjeuMainService = enjeuMainService;
	}
	
	// Web form to create a player
	
}
