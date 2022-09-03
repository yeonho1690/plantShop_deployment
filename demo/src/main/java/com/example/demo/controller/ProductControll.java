package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ImageFile;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ImageFileService;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Controller
public class ProductControll {
	
	@Autowired
	ImageFileService fileService;
	
	@Autowired
	private final ProductService productService;
	
	private final ProductRepository productRepository;
	
	
	@PostMapping("/register")
	public ResponseEntity<Product> registerProduct(@RequestPart(value = "pdata") Product product, @RequestPart(value = "pfile") MultipartFile file) {
		try {
			ImageFile imageFile = fileService.save(file); // 뷰에서 받아온 이미지 파일 저장
			
			// 이미지 포함 상품 정보 저장
			Product _product = this.productRepository.save(
					new Product(product.getPname(), product.getPtype(), product.getPprice(), product.getPstock(), imageFile.getFid(), product.getPdetail()));
			return new ResponseEntity<>(_product, HttpStatus.CREATED);
					
		} catch( Exception e ) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
