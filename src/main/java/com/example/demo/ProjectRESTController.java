package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectRESTController {
	
	@Autowired
	ProductService productService;
	
	
	@RequestMapping("/products")
	public List<Product>  getallproducts()
	{
		List<Product> l1 =productService.getallproducts();
		return l1;
	}

	@RequestMapping(method=RequestMethod.POST,value="/products")
	public void addproduct(@RequestBody Product product)
	{
		 productService.addproduct(product);
	 
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/products/{name}")
	public void removeproduct(@PathVariable String name)
	{
		 productService.removeproduct(name);
	 
	}

}
