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
@Table(name = "appointment")
public class Appointment {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "date", nullable = false)
	private Date date;
	
	
	@Column(name="name", nullable = false)
	private String name;
	
	
	@Column(name = "name_stylist", nullable = false)
	private String nameStylist;
	
	
	@Column(name = "phone", nullable = false)
	private String phone; 
	
	
	@Column(name = "time", nullable = false)
	private String time;

	@Column(name="combo_id", nullable = false)
	private int comboId;
	
	
	@Column(name = "message")
	private String message;
	
	
	@ManyToOne
	@JoinColumn(name = "combo_id",insertable = false, updatable = false)
	private Combo combo;

	public Appointment() {
		super();
	}

	public Appointment(int id, String code, Date date, String name, String nameStylist, String phone, String time,
			int comboId, String message, Combo combo) {
		super();
		this.id = id;
		this.code = code;
		this.date = date;
		this.name = name;
		this.nameStylist = nameStylist;
		this.phone = phone;
		this.time = time;
		this.comboId = comboId;
		this.message = message;
		this.combo = combo;
	}





	public Appointment(int id, String code, Date date, String name, String nameStylist, String phone, String time,
			int comboId, Combo combo) {
		super();
		this.id = id;
		this.code = code;
		this.date = date;
		this.name = name;
		this.nameStylist = nameStylist;
		this.phone = phone;
		this.time = time;
		this.comboId = comboId;
		this.combo = combo;
	}

	public Appointment(int id, String code, Date date, String name, String nameStylist, String phone, String time) {
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

	public int getComboId() {
		return comboId;
	}

	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
