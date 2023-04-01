package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository ProductRepository;
	
	public List<Product> getallproducts()
	{
		List<Product> l1=new ArrayList<Product>();
		
		ProductRepository.findAll().forEach(l1::add);
		return l1;
	}

	public void addproduct(Product product)
	{
		ProductRepository.save(product);
	 
	}
	
	public void removeproduct(String name)
	{
		ProductRepository.deleteById(name);
	 
	}

}
