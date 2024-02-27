package com.ty.shoppers.stack.util;

import java.util.List;

import com.ty.shoppers.stack.entity.Orders;

public class OrderHelper {
	private Orders order;
	private List<Integer> productIds;
	
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public List<Integer> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}
	
	
}
