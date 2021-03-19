package com.brocels.springboot.enjeu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.brocels.springboot.enjeu.service.PlayerService;

@Controller
public class PlayerlistController {

	private PlayerService playerService;
	private Map<String, Object> model;

	@Autowired
	public PlayerlistController(PlayerService playerService) {
		super();
		this.playerService = playerService;
		this.model = new HashMap<String, Object>();
	}
	
	public Map<String, Object> getPlayerlistModel() {
		
		model.put("players", playerService.getPlayers());
		model.put("numberOfPlayers", playerService.getPlayersSize());
		
		return model;
	}
	
	
}
