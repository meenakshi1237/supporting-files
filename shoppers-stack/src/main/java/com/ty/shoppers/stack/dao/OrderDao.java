package com.ty.shoppers.stack.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppers.stack.entity.Orders;
import com.ty.shoppers.stack.repository.OrderRepository;

@Repository
public class OrderDao {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Orders createOrder(Orders order){
		return orderRepository.save(order);
	}
	
	public Orders findOrderById(int orderId) {
		Optional<Orders> opt= orderRepository.findById(orderId);
		if(opt!=null) {
			return opt.get();
		}
		else {
			return null;
		}
	}
	
}
