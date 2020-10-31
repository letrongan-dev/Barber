package com.myproject.dto;

public class AuthChangePasswordDto {

	private int id; 
	private String code;
	private String oldPassword;
	private String newPassword;
	
	public AuthChangePasswordDto(String code, String oldPassword) {
		this.code = code;
		this.oldPassword = oldPassword;
	}
	
	public AuthChangePasswordDto(int id, String oldPassword, String newPassword) {
		super();
		this.id = id;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	
	public AuthChangePasswordDto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
