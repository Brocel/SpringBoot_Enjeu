package com.brocels.springboot.enjeu;

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

import com.brocels.springboot.enjeu.controller.PlayerCreationController;
import com.brocels.springboot.enjeu.controller.PlayerlistController;
import com.brocels.springboot.enjeu.domain.Player;
import com.brocels.springboot.enjeu.exception.DuplicateNameException;

@Controller
public class EnjeuDispatcher {

	private PlayerlistController playerlistController;
	private PlayerCreationController playerCreationController;
	
	private final Logger logger = LoggerFactory.getLogger(EnjeuDispatcher.class);

	@Autowired
	public EnjeuDispatcher(PlayerlistController playerlistController, PlayerCreationController playerCreationController) {
		super();
		this.playerlistController = playerlistController;
		this.playerCreationController = playerCreationController;
	}

	// Adding a web form
	@GetMapping("/home_createPlayerForm")
	public ModelAndView showCreatePlayerForm(@RequestParam(required = false) Integer id) {
		
		logger.info("HTTP GET Request received at /createPlayerForm URL");
		
		String viewName = "home_createPlayerForm";
		
		Map<String, Object> model = playerCreationController.getPlayerCreationModel(id);
		
		return new ModelAndView(viewName, model);
	}
	

	// Post mapping for the create player form
	@PostMapping("/home_createPlayerForm")
	public ModelAndView submitPlayerCreationForm(@Valid Player player, BindingResult bindingResult) {

		logger.info("HTTP POST Request received at /createPlayerForm URL");
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("home_createPlayerForm");
		}
		
		try {
			playerCreationController.postPlayerCreationModel(player);
		} catch (DuplicateNameException e) {
			bindingResult.rejectValue("name","", e.getMessage());
			return new ModelAndView("home_createPlayerForm");
		}
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/home_login");
		
		return new ModelAndView(redirect);
	}
	
	// Player List
	@GetMapping("/enjeu_playerlist")
	public ModelAndView getPlayerlist() {
		
		logger.info("HTTP GET Request received at /playerlist URL");
		
		String viewName = "enjeu_playerlist";
		
		Map<String, Object> model = playerlistController.getPlayerlistModel();
		
		return new ModelAndView(viewName, model);	
	}
}
