package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ShoppingCartService { 
	  @Autowired
	  private ProductRepository productRepository;

	  @Autowired
	  private ShoppingCartRepository cartRepository;

	  @Transactional
	  public void addItemToCart(String sessionId, String productname, int quantity) {
	    Product product = productRepository.findByname(productname);

	    ShoppingCart cart = cartRepository.findBysessionId(sessionId);

	    ShoppingCartItem item = cart.getItems().stream()
	      .filter(i -> i.getProduct().getName().equals(productname))
	      .findFirst()
	      .orElseGet(() -> new ShoppingCartItem(null, product, cart, 0));

	    item.setQuantity(item.getQuantity() + quantity);
	  
	    if (item.getQuantity() == 0) {
	      cart.getItems().remove(item);
	    } else if (!cart.getItems().contains(item)) {
	      cart.getItems().add(item);
	    }

	    cartRepository.save(cart);
	  }

	  public void removeItemFromCart(String sessionId, String productname) {
	    ShoppingCart cart = cartRepository.findBysessionId(sessionId);

	    ShoppingCartItem item = cart.getItems().stream()
	      .filter(i -> i.getProduct().getName().equals(productname))
	      .findFirst()
	      .orElseThrow(() -> new IllegalArgumentException("Product not found in cart"));

	    cart.getItems().remove(item);

	    cartRepository.save(cart);
	  }

	  @Transactional
	  public void checkout(String sessionId) throws Exception {
		  ShoppingCart cart = cartRepository.findBysessionId(sessionId);
 
        if (cart == null) {
            throw new Exception("Cart not found for user ");
        }
        
        // Update the quantity available in the product repository and create a new order
        List<Product> products =cart.getItems().stream().map(p->p.getProduct()).collect(Collectors.toList());
        
         
        for (Product product : products) {
            Product dbProduct = productRepository.findByname(product.getName());
            
            if (dbProduct.getQuantity() < product.getQuantity()) {
                throw new Exception("Insufficient quantity available for product with ID: " + product.getName());
            }
            
            dbProduct.setQuantity(dbProduct.getQuantity() - product.getQuantity());
            productRepository.save(dbProduct);
        }
        
      
        
        cartRepository.delete(cart); // Clear the user's cart
    
 

 

}
	 

}