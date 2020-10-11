package com.myproject.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ComboDto {
	
	private int id;
	@NotEmpty(message = "Tên không được để trống")
	@Size(min=5, max=20 ,message = "Tên phải có 5-20 ký tự")
	private String name;
	@NotNull(message = "Giá tiền không được để trống")
	private int price;
	@NotEmpty(message = "Bước 1 không được để trống")
	private String step1;
	@NotEmpty(message = "Bước 2 không được để trống")
	private String step2;
	@NotEmpty(message = "Bước 3 không được để trống")
	private String step3;
	private String step4;
	private String step5;
	
	public ComboDto() {
		
	}

	public ComboDto(int id, String name, int price, String step1, String step2, String step3, String step4, String step5) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.step1 = step1;
		this.step2 = step2;
		this.step3 = step3;
		this.step4 = step4;
		this.step5 = step5;
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

	public String getStep1() {
		return step1;
	}

	public void setStep1(String step1) {
		this.step1 = step1;
	}

	public String getStep2() {
		return step2;
	}

	public void setStep2(String step2) {
		this.step2 = step2;
	}

	public String getStep3() {
		return step3;
	}

	public void setStep3(String step3) {
		this.step3 = step3;
	}

	public String getStep4() {
		return step4;
	}

	public void setStep4(String step4) {
		this.step4 = step4;
	}

	public String getStep5() {
		return step5;
	}

	public void setStep5(String step5) {
		this.step5 = step5;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
