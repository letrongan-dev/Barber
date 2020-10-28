package com.myproject.dto;

import java.util.Date;

import com.myproject.entity.Combo;
import com.myproject.entity.User;

public class BillDto {

	private int id;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private User user;
	
	private Combo combo;
	
	private double total;
	
	private String note;
	
	private int userId;
	
	private int comboId;
	
	private String phone;
	
	private String nameCustomer;
	
	private int appointmentId;
	
	private int status;
	
	
	public BillDto() {
		super();
	}
	
	public BillDto(int id, Date createdAt, Date updatedAt, double total, String note, int userId, int comboId, int appointmentId, int status) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.total = total;
		this.note = note;
		this.userId = userId;
		this.comboId = comboId;
		this.appointmentId = appointmentId;
		this.status = status;
	}



	public BillDto(int id, Date createdAt, Date updatedAt, double total) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.total = total;
	}
	
	
	public BillDto(int id, Date createdAt, Date updatedAt, double total, String note, int userId, int comboId,
			String phone, String nameCustomer) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.total = total;
		this.note = note;
		this.userId = userId;
		this.comboId = comboId;
		this.phone = phone;
		this.nameCustomer = nameCustomer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getComboId() {
		return comboId;
	}

	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
