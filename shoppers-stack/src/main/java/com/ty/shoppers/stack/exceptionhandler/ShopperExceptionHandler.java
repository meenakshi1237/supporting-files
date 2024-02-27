package com.ty.shoppers.stack.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.shoppers.stack.dto.ResponseStructure;
@ControllerAdvice
public class ShopperExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ShopperDoesNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> UserNotFoundExceptionHandler(ShopperDoesNotFoundException exception){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setData(exception.getMessage());
		responseStructure.setMessage(exception.getMessage());
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidUserOperationException.class)
	public ResponseEntity<ResponseStructure<String>> invalidUserOperstionException(InvalidUserOperationException exception){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setData(exception.getMessage());
		responseStructure.setMessage(exception.getMessage());
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	
}
