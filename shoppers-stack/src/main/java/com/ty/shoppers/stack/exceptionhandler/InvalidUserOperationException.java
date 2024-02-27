package com.ty.shoppers.stack.exceptionhandler;

public class InvalidUserOperationException extends RuntimeException{
	
	private String message;

	public InvalidUserOperationException() {

	}

	public InvalidUserOperationException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
