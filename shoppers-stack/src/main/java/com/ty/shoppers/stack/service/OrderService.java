package com.ty.shoppers.stack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.shoppers.stack.dao.OrderDao;
import com.ty.shoppers.stack.dao.ProductDao;
import com.ty.shoppers.stack.dao.ShopperDao;
import com.ty.shoppers.stack.dto.ResponseStructure;
import com.ty.shoppers.stack.entity.Orders;
import com.ty.shoppers.stack.entity.Product;
import com.ty.shoppers.stack.entity.Shopper;
import com.ty.shoppers.stack.exceptionhandler.InvalidUserOperationException;
import com.ty.shoppers.stack.exceptionhandler.ShopperDoesNotFoundException;
import com.ty.shoppers.stack.util.OrderHelper;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ShopperDao shopperDao;
	@Autowired
	private ProductDao productDao;
	
	public ResponseEntity<ResponseStructure<Orders>> createOrder(OrderHelper orderHealper,int shopperId){
		Orders order=orderHealper.getOrder();
		List<Integer> productIds=orderHealper.getProductIds();
		Shopper shopper=shopperDao.findShopperById(shopperId);
		List<Product> products=productDao.FindAllProducts(productIds);
		if(shopper!=null) {
			if(shopper.getRole().equals("customer")) {
				order.setShopper(shopper);
				order.setProducts(products);
				Orders savedorder=orderDao.createOrder(order);
				
				ResponseStructure<Orders> responseStructure=new ResponseStructure<>();
				responseStructure.setData(savedorder);
				responseStructure.setMessage("sucess");
				responseStructure.setStatuscode(HttpStatus.OK.value());
				
				return new ResponseEntity<ResponseStructure<Orders>>(responseStructure,HttpStatus.OK);
			}
			else {
				throw new InvalidUserOperationException("invalid user");
			}
		}
		else {
			throw new ShopperDoesNotFoundException("shopper not exist");
		}
	}
	
	public ResponseEntity<ResponseStructure<Orders>> findOrderById(int shopperId,int orderId){
		Shopper shopper=shopperDao.findShopperById(shopperId);
		if(shopper!=null && shopper.getRole().equalsIgnoreCase("customer")) {
			List<Orders> shopperOrders=shopper.getOrders();
			Orders order=orderDao.findOrderById(orderId);
			if(shopperOrders.contains(order)) {
				ResponseStructure<Orders> responseStructure=new ResponseStructure<>();
				responseStructure.setData(order);
				responseStructure.setMessage("sucess");
				responseStructure.setStatuscode(HttpStatus.OK.value());
				
				return new ResponseEntity<ResponseStructure<Orders>>(responseStructure,HttpStatus.OK);
			}
			else {
				throw new InvalidUserOperationException("invalid user");
			}
		}
		else {
			throw new ShopperDoesNotFoundException("Shopper doesnot exist");
		}
	}
}
