package com.ty.shoppers.stack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.shoppers.stack.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{
	
}
