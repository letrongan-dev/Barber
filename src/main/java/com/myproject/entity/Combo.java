package com.myproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "combo")
@Entity
public class Combo {

	@Column(name = "id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name="price", nullable = false)
	private int price;
	
	@Column(name = "step1", nullable = false)
	private String step1;
	
	@Column(name = "step2", nullable = false)
	private String step2;
	
	@Column(name = "step3", nullable = false)
	private String step3;
	
	@Column(name = "step4")
	private String step4;
	
	@Column(name = "step5", nullable = false)
	private String step5;

	public Combo() {
		super();
	}

	public Combo(int id, String name, int price ,String step1, String step2, String step3, String step4, String step5) {
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
