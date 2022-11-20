package com.inops.visitorpass.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class VisitorException extends RuntimeException{
	
	private String message;
	private Throwable cause;
	

	public VisitorException(String message, Throwable cause)
	{
		super();
		this.message = message;
		this.cause = cause;
	}
}
