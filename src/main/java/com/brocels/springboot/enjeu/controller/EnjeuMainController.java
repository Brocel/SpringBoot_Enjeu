package com.brocels.springboot.enjeu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.brocels.springboot.enjeu.domain.Player;
import com.brocels.springboot.enjeu.exception.DuplicateNameException;
import com.brocels.springboot.enjeu.exception.PlayerlistFullException;
import com.brocels.springboot.enjeu.service.PlayerService;

@Controller
public class EnjeuMainController {

	private PlayerService playerService;
	
	private final Logger logger = LoggerFactory.getLogger(EnjeuMainController.class);

	@Autowired
	public EnjeuMainController(PlayerService playerService) {
		super();
		this.playerService = playerService;
	}
	
	// Mappings
	@GetMapping("/playerlist")
	public ModelAndView getPlayerlist() {
		
		logger.info("HTTP GET Request received at /playerlist URL");
		
		String viewName = "playerlist";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("players", playerService.getPlayers());
		model.put("numberOfPlayers", playerService.getPlayersSize());
		
		return new ModelAndView(viewName, model);	
	}
	// Adding a web form
	@GetMapping("/createPlayerForm")
	public ModelAndView showCreatePlayerForm(@RequestParam(required = false) Integer id) {
		
		logger.info("HTTP GET Request received at /createPlayerForm URL");
		
		String viewName = "createPlayerForm";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		Player player = playerService.findPlayerById(id);
		
		if (player == null) {
			model.put("player", new Player());
		} else {
			model.put("player", player);
		}
		
		return new ModelAndView(viewName, model);
	}
	

	// Post mapping for the create player form
	@PostMapping("/createPlayerForm")
	public ModelAndView submitWatchlistItemForm(@Valid Player player, BindingResult bindingResult) {

		logger.info("HTTP POST Request received at /createPlayerForm URL");
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("createPlayerForm");
		}
		
		
		try {
			playerService.addOrUpdateWatchlistItem(player);
		} catch (PlayerlistFullException e) {
			bindingResult.rejectValue(null, "", e.getMessage());
			return new ModelAndView("createPlayerForm");
		} catch (DuplicateNameException e) {
			bindingResult.rejectValue("title", "", e.getMessage());
			return new ModelAndView("createPlayerForm");
		}
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/playerlist");
		
		return new ModelAndView(redirect);
	}
}
