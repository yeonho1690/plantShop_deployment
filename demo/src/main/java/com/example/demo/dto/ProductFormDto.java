package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.Getter;
import lombok.Setter;

@CrossOrigin(origins = "*", maxAge = 3600)
@Getter
@Setter
public class ProductFormDto {
	
	@NotEmpty(message = "��ǰ���� �ʼ� �Դϴ�.")
	@Size(max=50)
	private String pname;
	
	@NotEmpty(message="ī�װ��� �ʼ� �Դϴ�.")
	private String ptype;
	
	@NotEmpty(message = "�����Է��� �ʼ� �Դϴ�.")
	private String pprice;
	
	@NotEmpty(message = "����Է��� �ʼ� �Դϴ�.")
	private String pstock;
	
	private String pImag1;
	
	@NotEmpty(message = "��ǰ������ �ʼ� �Դϴ�.")
	@Size(max=100)
	private String pDetail;
	
	public ProductFormDto() {
		
	}
	
//	public ProductFormDto(@NotEmpty(message = "��ǰ���� �ʼ� �Դϴ�.")@Size(max=50) private String pname,
//	@NotEmpty(message="ī�װ��� �ʼ� �Դϴ�.") private String ptype	) {
//		super();
//		this.pname = pname;
//		this.ptype = ptype;
//	}
	
}
