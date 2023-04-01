package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
	public ShoppingCart findBysessionId(String sessionId);

}
