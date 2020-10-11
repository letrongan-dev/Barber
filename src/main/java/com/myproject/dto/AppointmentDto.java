package com.myproject.dto;

import java.util.Date;



public class AppointmentDto {
	
	
	private int id;
	private String code;
	private Date date;
	private String name;
	private String nameStylist;
	private String phone;
	private String time;
	private String message;
	private int comboId;
	
	
	public AppointmentDto() {
		
	}

	
	

	public AppointmentDto(int id, String code, Date date, String name, String nameStylist, String phone, String time,
			String message, int comboId) {
		super();
		this.id = id;
		this.code = code;
		this.date = date;
		this.name = name;
		this.nameStylist = nameStylist;
		this.phone = phone;
		this.time = time;
		this.message = message;
		this.comboId = comboId;
	}




	public AppointmentDto(int id, String code, Date date, String name, String nameStylist, String phone, String time) {
		super();
		this.id = id;
		this.code = code;
		this.date = date;
		this.name = name;
		this.nameStylist = nameStylist;
		this.phone = phone;
		this.time = time;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNameStylist() {
		return nameStylist;
	}


	public void setNameStylist(String nameStylist) {
		this.nameStylist = nameStylist;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}




	public int getComboId() {
		return comboId;
	}




	public void setComboId(int comboId) {
		this.comboId = comboId;
	}
	
	
}
