package com.brocels.springboot.enjeu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.brocels.springboot.enjeu.domain.Player;
import com.brocels.springboot.enjeu.exception.DuplicateNameException;
import com.brocels.springboot.enjeu.exception.PlayerlistFullException;
import com.brocels.springboot.enjeu.service.PlayerService;

@Controller
public class PlayerCreationController {

	private PlayerService playerService;
	private Map<String, Object> model;
	
	@Autowired
	public PlayerCreationController(PlayerService playerService) {
		super();
		this.playerService = playerService;
		this.model = new HashMap<String, Object>();
	}
	
	public Map<String, Object> getPlayerCreationModel(Integer id) {
		
		Player player = playerService.findPlayerById(id);
		
		if (player == null) {
			model.put("player", new Player());
		} else {
			model.put("player", player);
		}
		
		return model;
	}
	
	public void postPlayerCreationModel(@Valid Player player) throws DuplicateNameException, PlayerlistFullException {
		
		playerService.addOrUpdatePlayer(player);
		
	}
}
