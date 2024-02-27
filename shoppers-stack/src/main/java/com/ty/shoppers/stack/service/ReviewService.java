package com.ty.shoppers.stack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.shoppers.stack.dao.ProductDao;
import com.ty.shoppers.stack.dao.ReviewDao;
import com.ty.shoppers.stack.dao.ShopperDao;
import com.ty.shoppers.stack.dto.ResponseStructure;
import com.ty.shoppers.stack.entity.Orders;
import com.ty.shoppers.stack.entity.Product;
import com.ty.shoppers.stack.entity.Review;
import com.ty.shoppers.stack.entity.Shopper;
import com.ty.shoppers.stack.exceptionhandler.InvalidUserOperationException;
import com.ty.shoppers.stack.exceptionhandler.ShopperDoesNotFoundException;

@Service
public class ReviewService {
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private ShopperDao shopperDao;
	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<Review>> createReview(Review review, int shopperId, int productId) {
		Shopper shopper = shopperDao.findShopperById(shopperId);
		Product product = productDao.findProductById(productId);
		if (shopper != null && product != null) {
			if (shopper.getRole().equalsIgnoreCase("customer")) {
				List<Orders> orders=shopper.getOrders();
				for(Orders order:orders) {
					if(order.getProducts().contains(product)) {
						review.setProduct(product);
						Review savedReview = reviewDao.createReview(review);
						ResponseStructure<Review> responseStructure = new ResponseStructure<>();
						responseStructure.setData(savedReview);
						responseStructure.setMessage("sucess");
						responseStructure.setStatuscode(HttpStatus.OK.value());

						return new ResponseEntity<ResponseStructure<Review>>(responseStructure, HttpStatus.OK);
					}
					else {
						throw new InvalidUserOperationException("order before giving review");
					}
				}
				
			} 
			else {
				throw new InvalidUserOperationException("invalid user");
			}
		} 
		
		throw new ShopperDoesNotFoundException("User Not Found");
		
		
	}

	public ResponseEntity<ResponseStructure<List<Review>>> findAllReviewsByProductId(int productId) {

		Product product = productDao.findProductById(productId);
		if (product != null) {

			List<Review> reviews = reviewDao.finddAllReviewsByProductId(productId);

			ResponseStructure<List<Review>> responseStructure = new ResponseStructure<>();
			responseStructure.setData(reviews);
			responseStructure.setMessage("sucess");
			responseStructure.setStatuscode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<Review>>>(responseStructure, HttpStatus.OK);

		} else {
			throw new ShopperDoesNotFoundException("Product Doesnot Exist");
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> removeReview(int reviewId,int shopperId){
		Shopper shopper=shopperDao.findShopperById(shopperId);
		Review review=reviewDao.findByReviewId(reviewId);
		if(shopper!=null) {
			if(shopper.getRole().equalsIgnoreCase("merchant")) {
				/*
				 * here iam finding product through review id 
				 * and verifying whether that product belongs to that shopper or not
				 */
				Product product=review.getProduct();
				List<Product> products=shopper.getProducts();
				if(products.contains(product)) {
					String removedReview=reviewDao.removeReview(reviewId);
					ResponseStructure<String> responseStructure = new ResponseStructure<>();
					responseStructure.setData(removedReview);
					responseStructure.setMessage("sucess");
					responseStructure.setStatuscode(HttpStatus.OK.value());
					
					return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
				}
				else {
					throw new InvalidUserOperationException("invalid user");
				}
				
			}
			else {
				throw new InvalidUserOperationException("invalid user");
			}
		}
		else {
			throw new ShopperDoesNotFoundException("shopper not exist");
		}
	}

}
