package com.myproject.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity//Khai báo lớp ánh xa
@Table (name = "users") //Ánh xạ đến bảng nào
public class User {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	
	@Column(name="code",nullable = false, unique = true)
	private String code;
	
	@Column(name="name", nullable = false)
	
	private String name;
	
	@Column(name="role_id", nullable = false)
	private int roleId;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone",nullable = false)
	private String phone;
	
	@Column(name = "avatar")
	private String avatar;
	
	@ManyToOne
	@JoinColumn(name = "role_id",insertable = false, updatable = false)
	private Role role;
	
	
	public User() {};

	public User(int id, String code, String name, int roleId, String email, String password, String address,
			String phone, Role role) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.roleId = roleId;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.role = role;
	}
	public User(String code, String name, int roleId, String email, String address, String phone,
			String avatar) {
		super();
		this.code = code;
		this.name = name;
		this.roleId = roleId;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.avatar = avatar;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
