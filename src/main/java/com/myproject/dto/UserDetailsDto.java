package com.myproject.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetailsDto extends User {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String fullname;
	private String avatar;
	private String email;
	private String password;
	private String phone;
	private String address;
	private String code;
	private User user;

	public UserDetailsDto(String username, String password, Collection<? extends GrantedAuthority> authorities,int id, String fullname, String avatar, String phone, String address, String email, String code) {
		super(username, password, authorities);
		this.id = id;
		this.fullname = fullname;
		this.avatar = "/upload/user/"+avatar;
		this.address = address;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.code = code;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
