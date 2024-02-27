package com.ty.shoppers.stack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.shoppers.stack.entity.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	List<Product> findProductByShopperShopperId(int shopperId);
	
	@Transactional
	@Modifying
	@Query("delete from Product p where p.productId=:productId")
	void deleteProductById(int productId);
	
}
