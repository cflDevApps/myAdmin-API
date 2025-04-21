package com.cflApps.MyAdmin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MyAdminServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MyAdminServiceException(String message) {
		super(message);
	}
	

}
