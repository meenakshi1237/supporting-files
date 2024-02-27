package com.ty.shoppers.stack.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppers.stack.entity.Orders;
import com.ty.shoppers.stack.entity.Product;
import com.ty.shoppers.stack.entity.Shopper;
import com.ty.shoppers.stack.repository.ProductRepository;
import com.ty.shoppers.stack.repository.ShopperRepository;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepository productRepository;

	public Product createProduct(Product product) {

		return productRepository.save(product);
	}

	public Product findProductById(int productId) {
		Optional<Product> opt = productRepository.findById(productId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> findAllProductsByShopperId(int shopperId) {
		return productRepository.findProductByShopperShopperId(shopperId);
	}

	public List<Product> FindAllProducts(List<Integer> productIds) {
		List<Product> products = productRepository.findAllById(productIds);
		return products;
	}

	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	public String removeProduct(Product product) {
		System.err.println("delete method");
		productRepository.delete(product);
		System.err.println("delete method");
		return "product removed";
	}

}
