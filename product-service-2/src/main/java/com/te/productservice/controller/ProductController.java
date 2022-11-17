package com.te.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.productservice.response.Response;
import com.te.productservice.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public ResponseEntity<Response> getProduct(@RequestParam String productName) {
		return ResponseEntity.ok(Response.builder().isError(false).message("Product Fetched Successfully")
				.data(productService.getProduct(productName)).build());
	}

}
