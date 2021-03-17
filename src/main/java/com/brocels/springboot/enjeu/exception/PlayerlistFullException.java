package com.brocels.springboot.enjeu.exception;

import com.brocels.springboot.enjeu.repository.PlayerRepository;

public class PlayerlistFullException extends Exception {

	private int maximumPlayers = PlayerRepository.getMaximumPlayers();
	
	private String errorMessage = "List of players is full, it already contains " + maximumPlayers + " players";

	@Override
	public String getMessage() {
		return errorMessage;
	}
}
