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

import com.brocels.springboot.enjeu.controller.LoginController;
import com.brocels.springboot.enjeu.controller.PlayerCreationController;
import com.brocels.springboot.enjeu.controller.PlayerlistController;
import com.brocels.springboot.enjeu.domain.Player;
import com.brocels.springboot.enjeu.exception.DuplicateNameException;
import com.brocels.springboot.enjeu.exception.PlayerlistFullException;

@Controller
public class EnjeuDispatcher {

	private LoginController loginController;
	private PlayerlistController playerlistController;
	private PlayerCreationController playerCreationController;
	
	private final Logger logger = LoggerFactory.getLogger(EnjeuDispatcher.class);

	@Autowired
	public EnjeuDispatcher(LoginController loginController, PlayerlistController playerlistController, PlayerCreationController playerCreationController) {
		super();
		this.loginController = loginController;
		this.playerlistController = playerlistController;
		this.playerCreationController = playerCreationController;
	}
	
	// Player List
	@GetMapping("/login")
	public ModelAndView getLogin() {
		
		logger.info("HTTP GET Request received at /login URL");
		
		String viewName = "login";
		
		Map<String, Object> model = loginController.getLoginModel();
		
		return new ModelAndView(viewName, model);
	}
	
	// Player List
	@GetMapping("/playerlist")
	public ModelAndView getPlayerlist() {
		
		logger.info("HTTP GET Request received at /playerlist URL");
		
		String viewName = "playerlist";
		
		Map<String, Object> model = playerlistController.getPlayerlistModel();
		
		return new ModelAndView(viewName, model);	
	}
	// Adding a web form
	@GetMapping("/createPlayerForm")
	public ModelAndView showCreatePlayerForm(@RequestParam(required = false) Integer id) {
		
		logger.info("HTTP GET Request received at /createPlayerForm URL");
		
		String viewName = "createPlayerForm";
		
		Map<String, Object> model = playerCreationController.getPlayerCreationModel(id);
		
		return new ModelAndView(viewName, model);
	}
	

	// Post mapping for the create player form
	@PostMapping("/createPlayerForm")
	public ModelAndView submitPlayerCreationForm(@Valid Player player, BindingResult bindingResult) {

		logger.info("HTTP POST Request received at /createPlayerForm URL");
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("createPlayerForm");
		}
		
		try {
			playerCreationController.postPlayerCreationModel(player);
		} catch (PlayerlistFullException e) {
			bindingResult.rejectValue(null, "", e.getMessage());
			return new ModelAndView("createPlayerForm");
		} catch (DuplicateNameException e) {
			bindingResult.rejectValue("name", "", e.getMessage());
			return new ModelAndView("createPlayerForm");
		}
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/playerlist");
		
		return new ModelAndView(redirect);
	}
}
