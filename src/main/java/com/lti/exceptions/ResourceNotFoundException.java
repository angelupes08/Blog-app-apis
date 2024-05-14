package com.lti.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private int id;

	public ResourceNotFoundException(String message) {
		super(message);
		
	}
	
	
}
