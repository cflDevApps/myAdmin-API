package com.cflApps.MyAdmin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ExistentUsernameException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExistentUsernameException(String message) {
		super(message);
	}
	

}
