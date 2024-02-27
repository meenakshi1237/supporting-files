package com.ty.shoppers.stack.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Shopper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "sho_id_gen")
	@SequenceGenerator(name = "sho_id_gen",initialValue = 101,allocationSize = 1)
	private int shopperId;
	@Column(nullable = false)
	private String shopperName;
	@Column(unique = true,nullable = false)
	private long shopperPhone;
	@Column(unique = true,nullable = false)
	private String shopperemail;
	@Column(nullable = false)
	private String shopperpassword;
	@Column(nullable = false)
	private String role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "shopper",cascade = CascadeType.ALL)
	private List<Product> products=new ArrayList<Product>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "shopper",cascade = CascadeType.ALL)
	private List<Orders> orders=new ArrayList<Orders>();

	public int getShopperId() {
		return shopperId;
	}

	public void setShopperId(int shopperId) {
		this.shopperId = shopperId;
	}

	public String getShopperName() {
		return shopperName;
	}

	public void setShopperName(String shopperName) {
		this.shopperName = shopperName;
	}

	public long getShopperPhone() {
		return shopperPhone;
	}

	public void setShopperPhone(long shopperPhone) {
		this.shopperPhone = shopperPhone;
	}

	public String getShopperemail() {
		return shopperemail;
	}

	public void setShopperemail(String shopperemail) {
		this.shopperemail = shopperemail;
	}

	public String getShopperpassword() {
		return shopperpassword;
	}

	public void setShopperpassword(String shopperpassword) {
		this.shopperpassword = shopperpassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	
	
}
