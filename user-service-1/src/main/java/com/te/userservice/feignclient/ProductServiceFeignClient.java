package com.te.userservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.te.userservice.response.Response;

@FeignClient(name = "product-service")
public interface ProductServiceFeignClient {
	
	@GetMapping("/products/product")
	public ResponseEntity<Response> getProduct(@RequestParam String productName);
}
