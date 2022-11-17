package com.te.productservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.productservice.entity.Product;
import com.te.productservice.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;

	public Product getProduct(String productName) {

		Optional<Product> findByProductName = repo.findByProductName(productName);

		return findByProductName.get();

	}

}
