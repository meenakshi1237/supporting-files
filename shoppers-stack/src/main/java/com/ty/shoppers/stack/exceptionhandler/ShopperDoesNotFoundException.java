package com.ty.shoppers.stack.exceptionhandler;

public class ShopperDoesNotFoundException extends RuntimeException {
	
	private String message;

	public ShopperDoesNotFoundException() {

	}

	public ShopperDoesNotFoundException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
