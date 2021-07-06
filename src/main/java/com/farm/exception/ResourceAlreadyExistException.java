package com.farm.exception;

public class ResourceAlreadyExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistException() {
	}

	public ResourceAlreadyExistException(String message) {
		super(message);
	}

}