package com.ty.shoppers.stack.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "ord_id_gen")
	@SequenceGenerator(name = "ord_id_gen",initialValue = 301,allocationSize = 1)
	private int orderId;
	private String orderDescription;
	@Column(nullable = false)
	private String delivaryLocation;
	@CreationTimestamp
	private LocalDateTime createdTime;
	
	@Schema(hidden = true)
	@ManyToOne
	@JoinColumn
	private Shopper shopper;
	
	@Schema(hidden = true)
	@ManyToMany(cascade = { CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(joinColumns = @JoinColumn,inverseJoinColumns = @JoinColumn)
	private List<Product> products=new ArrayList<Product>();

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public String getDelivaryLocation() {
		return delivaryLocation;
	}

	public void setDelivaryLocation(String delivaryLocation) {
		this.delivaryLocation = delivaryLocation;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public Shopper getShopper() {
		return shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
