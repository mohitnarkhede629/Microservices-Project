package com.te.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.productservice.entity.Product;
import com.te.productservice.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product getProduct(String productName) {
		return  productRepository.findByproductName(productName).get();
	}

}
