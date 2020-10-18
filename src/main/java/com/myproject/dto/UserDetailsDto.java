package com.myproject.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetailsDto extends User {
	private static final long serialVersionUID = 1L;
	
	private String fullname;
	private String avatar;

	public UserDetailsDto(String username, String password, Collection<? extends GrantedAuthority> authorities, String fullname, String avatar) {
		super(username, password, authorities);
		this.fullname = fullname;
		this.avatar = "/upload/user/"+avatar;
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
	
	
	
	
	
	
}
