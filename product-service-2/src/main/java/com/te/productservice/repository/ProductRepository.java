package com.te.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.productservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	Optional<Product> findByproductName(String name);
}
