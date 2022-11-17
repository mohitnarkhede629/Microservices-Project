package com.te.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.userservice.feignclient.ProductServiceFeignClient;
import com.te.userservice.response.Response;
import com.te.userservice.service.UserService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController

@RequestMapping("/users")
public class UserController {



	@Autowired
	private UserService service;

	@Autowired
	private ProductServiceFeignClient feignClient;
	
	
	
	@Retry(name = "getUser", fallbackMethod = "retryFallBackMethod")
	@GetMapping("/user")
	public ResponseEntity<Response> getUser(@RequestParam String userName) {
		return ResponseEntity.ok(Response.builder().isError(false).message("User Fetched Successfully")
				.data(service.getUser(userName)).build());
	}

	@CircuitBreaker(name = "getProduct",fallbackMethod = 
			"circuitFallBackMethod")
	@RateLimiter(name = "getProduct", fallbackMethod = "rateLimitFallBackMethod")
	@GetMapping("/product")
	public ResponseEntity<com.te.userservice.feignclient.response.Response> getProduct(@RequestParam String productName)  {
		return ResponseEntity.ok(com.te.userservice.feignclient.response.Response.builder().isError(false).message("User Fetched Successfully")
				.data(feignClient.getProduct(productName)).build());
	}
	
	@Bulkhead(name = "getProduct2", fallbackMethod = "bulkheadFallBackMethod",type = Type.SEMAPHORE )
	@GetMapping("/product2")
	public ResponseEntity<com.te.userservice.feignclient.response.Response> getProduct2(@RequestParam String productName)  {
		return ResponseEntity.ok(com.te.userservice.feignclient.response.Response.builder().isError(false).message("User Fetched Successfully")
				.data(feignClient.getProduct(productName)).build());
	}
	
	
	// FallBack Methods
	
	public ResponseEntity<Response> circuitFallBackMethod(Exception e) {
		return ResponseEntity.ok(Response.builder().isError(true).message("Something's Fishy").data(null).build());
	}

	public ResponseEntity<Response> rateLimitFallBackMethod(Exception e) {
		return ResponseEntity.ok(Response.builder().isError(true).message("Too Many Requests").data(null).build());
	}

	public ResponseEntity<Response> retryFallBackMethod(Exception e) {
		return ResponseEntity.ok(Response.builder().isError(true).message("Retrying!!!").data(null).build());
	}

	public ResponseEntity<Response> bulkheadFallBackMethod(Exception e) {
		return ResponseEntity.ok(Response.builder().isError(true).message("Bulkhead").data(null).build());
	}
	
	
	// Implementation Pending

	public ResponseEntity<Response> timeOutFallBackMethod(Exception e) {
		return ResponseEntity.ok(Response.builder().isError(true).message("Request Time Out!!!").data(null).build());
	}


}