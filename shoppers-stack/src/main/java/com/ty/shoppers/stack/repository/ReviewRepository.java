package com.ty.shoppers.stack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.shoppers.stack.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	
		List<Review> findReviewByProductProductId(int productId);
}
