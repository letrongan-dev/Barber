package com.myproject.dto;

import java.beans.Transient;

public class UserDto {

	private int id;
	private String code;
	private String name;
	private String phone;
	private String email;
	private String password;
	private String address;
	private int roleId;
	private String avatar;
	private String roleName;
	
	public UserDto() {}

	public UserDto(int id, String code, String name, String phone, String email, String password, String address,
			int roleId, String avatar) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.address = address;
		this.roleId = roleId;
		this.avatar = avatar;
	}
	
	public UserDto(int id, String code, String name, String phone, String email, String password, String address,
			int roleId, String avatar, String roleName) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.address = address;
		this.roleId = roleId;
		this.avatar = avatar;
		this.roleName = roleName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Transient
    public String getPhotosImagePath() {
        if (avatar == null) return null;
         
        return "/user-photos/" + avatar;
    }
}
