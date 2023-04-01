package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ShoppingCart {
	  @Id
	  private String sessionId;
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart")
	  private List<ShoppingCartItem> items = new ArrayList<>();

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}

	public ShoppingCart(String sessionId, List<ShoppingCartItem> items) {
		super();
		this.sessionId = sessionId;
		this.items = items;
	}

	public ShoppingCart() {
		super(); 
	}
	  
	  

}
