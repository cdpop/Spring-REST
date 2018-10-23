package com.pops.restWebService.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// define standard error structure
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

//	this by default gives a standard 404 error but using CustomizedResponseEntityExceptionHandler
//	we get to define it further
	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
