package com.myproject.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
	
	@Column(name="updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date updatedAt;
	
	@Column(name ="user_id", nullable = false)
	private int userId;
	
	@Column(name = "combo_id", nullable = false)
	private int comboId;
	
	@Column(name = "customer", nullable = false)
	private String nameCustomer;
	
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name="appointment_id", nullable = false)
	private int appointmentId;
	
	@Column(name = "status", columnDefinition = "integer default 0")
	private int status;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "combo_id",nullable = false, insertable = false, updatable = false)
	private Combo combo;
	
	@Column(name="total", columnDefinition = "double default 0")
	private double total;
	
	@ManyToOne
	@JoinColumn(name = "appointment_id",nullable = false, insertable = false, updatable = false)
	private Appointment appointment;
	
	@Column(name="note", length = 300)
	private String note;
	
	public Bill() {
		
	}
	
	public Bill(int id, Date createdAt, Date updatedAt, User user, Combo combo, double total, String note) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.combo = combo;
		this.total = total;
		this.note = note;
	}
	
	public Bill(int id, Date createdAt, Date updatedAt, int userId, int comboId, String nameCustomer, String phone,
			double total, String note) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userId = userId;
		this.comboId = comboId;
		this.nameCustomer = nameCustomer;
		this.phone = phone;
		this.total = total;
		this.note = note;
	}

	public Bill(int id, Date createdAt, Date updatedAt, double total) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.total = total;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
