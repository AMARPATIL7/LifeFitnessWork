package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class ShoppingController {
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	 
	
	@RequestMapping("/addtocart")
	public void addItemToCart(HttpServletRequest request,@RequestBody Product product)
	{
		HttpSession session = request.getSession();
		shoppingCartService.addItemToCart(session.getId(),product.getName(),product.getQuantity());
	}
	
	@RequestMapping("/removefromcart")
	public void removeItemFromCart(HttpServletRequest request,@RequestBody Product product)
	{
		HttpSession session = request.getSession();
		shoppingCartService.removeItemFromCart(session.getId(),product.getName());
	}
	
	@RequestMapping("/checkout")
	public void checkout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		try {
			shoppingCartService.checkout(session.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
