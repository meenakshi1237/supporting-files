package com.ty.shoppers.stack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.shoppers.stack.dao.ShopperDao;
import com.ty.shoppers.stack.dto.ResponseStructure;
import com.ty.shoppers.stack.entity.Product;
import com.ty.shoppers.stack.entity.Shopper;
import com.ty.shoppers.stack.exceptionhandler.InvalidUserOperationException;
import com.ty.shoppers.stack.exceptionhandler.ShopperDoesNotFoundException;

@Service
public class ShopperService {
	@Autowired
	private ShopperDao shopperdao;
	
	public ResponseEntity<ResponseStructure<Shopper>> createShopper(Shopper shopper){
		Shopper savedShopper=shopperdao.createShopper(shopper);
		ResponseStructure<Shopper> responseStructure=new ResponseStructure<Shopper>();
		responseStructure.setData(savedShopper);
		responseStructure.setMessage("sucess");
		responseStructure.setStatuscode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Shopper>>(responseStructure,HttpStatus.OK); 
	}
	
	public ResponseEntity<ResponseStructure<Shopper>> findShopperById(int shopperId){
		Shopper shopper=shopperdao.findShopperById(shopperId);
		if(shopper!=null) {
			
			ResponseStructure<Shopper> responseStructure=new ResponseStructure<>();
			responseStructure.setData(shopper);
			responseStructure.setMessage("sucess");
			responseStructure.setStatuscode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<Shopper>>(responseStructure,HttpStatus.OK); 
		}
		else {
			throw new InvalidUserOperationException("invalid user");
		}
	}
	
	public ResponseEntity<ResponseStructure<Shopper>> shopperLogin(String email,String password){
		Shopper shopper=shopperdao.shipperLogin(email, password);
		if(shopper!=null) {
			ResponseStructure<Shopper> responseStructure=new ResponseStructure<>();
			responseStructure.setData(shopper);
			responseStructure.setMessage("sucess");
			responseStructure.setStatuscode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<Shopper>>(responseStructure,HttpStatus.OK); 
		}
		else {
			throw new ShopperDoesNotFoundException("email or password incorrect");
		}
	}
}
