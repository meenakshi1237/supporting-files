package com.ty.shoppers.stack.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppers.stack.entity.Product;
import com.ty.shoppers.stack.entity.Shopper;
import com.ty.shoppers.stack.repository.ShopperRepository;
@Repository
public class ShopperDao {
	
	@Autowired
	private ShopperRepository shopperRepository;
	
	public Shopper createShopper(Shopper shopper) {
		return shopperRepository.save(shopper);
	}
	
	public Shopper findShopperById(int shopperId) {
		Optional<Shopper> opt=shopperRepository.findById(shopperId);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			return null;
		}
	}
	
	public Shopper shipperLogin(String email,String password) {
		return shopperRepository.findByShopperemailAndShopperpassword(email, password);
	}
	
	
}
