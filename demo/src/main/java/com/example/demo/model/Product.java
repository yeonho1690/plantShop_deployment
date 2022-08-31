package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter @Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	private String pname; // 상품명
	
	private String ptype; // 상품 카테고리
	
	private Integer pprice; // 상품 가격
	
	private Integer pstock; // 상품 수량
	
	private String pImg1; // 상품이미지1
//	
//	private String pImg2; // 상품이미지2
//	
//	private String pImg3; // 상품이미지3
	
	private String pdetail; // 상품 상세설명
	
	public Product() {
		
	}
	
	public Product(String pname, String ptype, Integer pprice, Integer pstock, String pImg1, String pdetail) {
		this.pname = pname;
		this.ptype = ptype;
		this.pprice = pprice;
		this.pstock = pstock;
		this.pImg1 = pImg1;
		this.pdetail = pdetail;
	}
	
}
