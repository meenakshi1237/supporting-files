package com.ty.shoppers.stack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.shoppers.stack.entity.Shopper;

public interface ShopperRepository extends JpaRepository<Shopper, Integer>{

	@Query("select s.role from Shopper s where s.shopperId=:id")
	String findRoleByShopperId(int id);
	
	
	Shopper findByShopperemailAndShopperpassword(String email,String password);
	
	
}
