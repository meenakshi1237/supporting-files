package com.ty.shoppers.stack.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "pro_id_gen")
	@SequenceGenerator(name = "pro_id_gen",initialValue = 501,allocationSize = 1)
	private int productId;
	
	private String productName;
	@Column(nullable = false)
	private double productCost;
	private String productType;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Shopper shopper;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<Orders> orders=new ArrayList<Orders>();
	
	@Schema(hidden = true)
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<Review> reviews=new ArrayList<Review>();

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductCost() {
		return productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Shopper getShopper() {
		return shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
	
	
}
