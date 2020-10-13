package com.myproject.dto;


import java.util.Date;


import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;



public class AppointmentDto {
	
	
	private int id;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date date;
	
	@NotNull(message = "Tên không được để trống")
	private String name;
	
	private String nameStylist;
	
	@NotNull(message = "Vui lòng nhập số điện thoại")
	private String phone;
	
	@NotNull(message = "Vui lòng nhập thời gian")
	private String time;
	
	private String message;
	
	@NotNull(message = "Vui lòng chọn combo")
	private int comboId;

	public AppointmentDto() {
		
	}
	public AppointmentDto(int id, Date date, String name, String nameStylist, String phone, String time,
			String message, int comboId) {
		super();
		this.id = id;
		this.date = date;
		this.name = name;
		this.nameStylist = nameStylist;
		this.phone = phone;
		this.time = time;
		this.message = message;
		this.comboId = comboId;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
