package com.ty.shoppers.stack.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.shoppers.stack.dao.ProductDao;
import com.ty.shoppers.stack.dao.ShopperDao;
import com.ty.shoppers.stack.dto.ResponseStructure;
import com.ty.shoppers.stack.entity.Orders;
import com.ty.shoppers.stack.entity.Product;
import com.ty.shoppers.stack.entity.Shopper;
import com.ty.shoppers.stack.exceptionhandler.InvalidUserOperationException;
import com.ty.shoppers.stack.exceptionhandler.ShopperDoesNotFoundException;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ShopperDao shopperDao;

	public ResponseEntity<ResponseStructure<Product>> createProduce(Product product, int shopperId) {
		Shopper shopper = shopperDao.findShopperById(shopperId);
		if (shopper != null) {
			if (shopper.getRole().equalsIgnoreCase("merchant")) {
				// i am setting shopper to this product
				// no need to add product in shopper list
				product.setShopper(shopper);
				Product savedproduct = productDao.createProduct(product);
				ResponseStructure<Product> responseStructure = new ResponseStructure<>();
				responseStructure.setData(savedproduct);
				responseStructure.setMessage("sucess");
				responseStructure.setStatuscode(HttpStatus.OK.value());

				return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
			} else {
				throw new InvalidUserOperationException("invalid user");
			}
		} else {
			throw new ShopperDoesNotFoundException("Shopper Not Exist");
		}
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {

		List<Product> products = productDao.findAllProducts();

		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();
		responseStructure.setData(products);
		responseStructure.setMessage("sucess");
		responseStructure.setStatuscode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducteByShopperId(int shopperId) {
		Shopper shopper = shopperDao.findShopperById(shopperId);
		if (shopper != null) {
			if (shopper.getRole().equals("merchant")) {
				List<Product> products = productDao.findAllProductsByShopperId(shopperId);

				ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();
				responseStructure.setData(products);
				responseStructure.setMessage("sucess");
				responseStructure.setStatuscode(HttpStatus.OK.value());

				return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.OK);

			} else {
				throw new InvalidUserOperationException("invali user");
			}
		} else {
			throw new ShopperDoesNotFoundException("shopper doesnot exist");
		}

	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product, int productId, int shopperId) {
		Product foundProduct = productDao.findProductById(productId);
		Shopper shopper = shopperDao.findShopperById(shopperId);
		if (foundProduct != null && shopper != null) {
			if (shopper.getRole().equals("merchant")) {
				product.setProductId(productId);
				product.setShopper(shopper);
				Product updatedProduct = productDao.updateProduct(product);
				ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
				responseStructure.setData(updatedProduct);
				responseStructure.setMessage("proudctUpdated");
				responseStructure.setStatuscode(HttpStatus.OK.value());

				return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
			} else {
				throw new InvalidUserOperationException("invalid user");
			}
		} else {
			throw new ShopperDoesNotFoundException("Product or shopper not exist");
		}
	}

	public ResponseEntity<ResponseStructure<String>> removeProduct(int productId, int shopperId) {
		Product foundProduct = productDao.findProductById(productId);
		Shopper shopper = shopperDao.findShopperById(shopperId);
		if (shopper != null && foundProduct != null) {
			if (shopper.getRole().equalsIgnoreCase("merchant")) {
				List<Product> shopperProducts = shopper.getProducts();
				if (shopperProducts.contains(foundProduct)) {
					List<Orders> orders=foundProduct.getOrders();
//					for(int i=0; i<orders.size();i++) {
//						orders.get(i).getProducts().removeAll(Arrays.asList(foundProduct));
//					}
					shopperProducts.remove(foundProduct);
					shopper.setProducts(shopperProducts);
					shopperDao.createShopper(shopper);
					//
					String removeProduct = productDao.removeProduct(foundProduct);
					ResponseStructure<String> responseStructure = new ResponseStructure<>();
					responseStructure.setData(removeProduct);
					responseStructure.setMessage("proudctRemoved");
					responseStructure.setStatuscode(HttpStatus.OK.value());

					return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
				} else {
					throw new InvalidUserOperationException("invalid user");
				}

			} else {
				throw new InvalidUserOperationException("invalid user");
			}
		} else {
			throw new ShopperDoesNotFoundException("Product or user not exist");
		}
	}

}
