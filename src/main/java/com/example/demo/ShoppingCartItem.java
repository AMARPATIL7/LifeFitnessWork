package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ShoppingCartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "cart_session_id")
  private ShoppingCart shoppingCart;

  private int quantity;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Product getProduct() {
	return product;
}

public void setProduct(Product product) {
	this.product = product;
}

public ShoppingCart getShoppingCart() {
	return shoppingCart;
}

public void setShoppingCart(ShoppingCart shoppingCart) {
	this.shoppingCart = shoppingCart;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public ShoppingCartItem(Long id, Product product, ShoppingCart shoppingCart, int quantity) {
	super();
	this.id = id;
	this.product = product;
	this.shoppingCart = shoppingCart;
	this.quantity = quantity;
}

public ShoppingCartItem() {
	super(); 
}
 
  
  
}