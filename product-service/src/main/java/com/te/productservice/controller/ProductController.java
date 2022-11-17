package com.te.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.productservice.entity.Product;
import com.te.productservice.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("product")
	public Product getProduct(@RequestParam String productName) {
		return productService.getProduct(productName);

	}

}
