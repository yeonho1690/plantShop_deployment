package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Controller
public class ProductControll {

	@Autowired
	private final ProductService productService;
	
	private final ProductRepository productRepository;
	
	
	@PostMapping("/register")
	public ResponseEntity<Product> registerProduct(@RequestBody Product product) {
		try {
			Product _product = this.productRepository.save(
					new Product(product.getPname(), product.getPtype(), product.getPprice(), product.getPstock(), product.getPImg1(), product.getPdetail()));
			return new ResponseEntity<>(_product, HttpStatus.CREATED);
					
		} catch( Exception e ) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
