package com.ty.shoppers.stack.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppers.stack.entity.Review;
import com.ty.shoppers.stack.repository.ReviewRepository;

@Repository
public class ReviewDao {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public Review createReview(Review review) {
		return reviewRepository.save(review);
	}
	
	public List<Review> finddAllReviewsByProductId(int productId){
		return reviewRepository.findReviewByProductProductId(productId);
	}
	
	public String removeReview(int reviewId) {
		reviewRepository.deleteById(reviewId);
		return "review Removed";
	}
	
	public Review findByReviewId(int reviewId) {
		Optional<Review> opt=reviewRepository.findById(reviewId);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			return null;
		}
	}
	
	
}
