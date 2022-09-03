package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter @Setter
public class ImageFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fid; // 이미지 파일 아이디
	
	private String name;
	
	private String type;
	
	@Lob
	private byte[] data;
	
	public ImageFile() {}
	public ImageFile(String name, String type, byte[] data) {
		this.name = name;
		this.type = type;
		this.data = data;
	}
}
