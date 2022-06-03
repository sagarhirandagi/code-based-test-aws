package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DiseaseNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DiseaseNotFoundException(String message) {
		super(message);
	}
}
