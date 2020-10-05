package com.myproject.dto;


import javax.persistence.Column;
import javax.validation.constraints.*;
	
public class RoleDto {
	
	private int id;
	
	@NotEmpty(message = "Tên không được để trống")
	@Size(min=5, max=20 ,message = "Tên phải có 5-20 ký tự")
	@Column(unique = true)
	private String name;
	
	
	@NotEmpty(message = "Mô tả không được để trống")
	private String desc;
	
	
	public RoleDto() {
	}
	public RoleDto(int id, String name, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
