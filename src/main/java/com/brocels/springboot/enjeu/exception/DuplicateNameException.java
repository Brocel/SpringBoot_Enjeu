package com.brocels.springboot.enjeu.exception;

public class DuplicateNameException extends Exception {

	private String errorMessage = "This player name already exists in players list";

	@Override
	public String getMessage() {
		return errorMessage;
	}
}
