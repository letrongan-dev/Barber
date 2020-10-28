package com.myproject.dto;

public class Error {

	private int status;
	private String message;
	private UserDto userdto;
	private Object obj;
	
	public Error(int status, String message, Object obj) {
		super();
		this.status = status;
		this.message = message;
		this.obj = obj;
	}
	public Error(int status, String message, UserDto userdto) {
		super();
		this.status = status;
		this.message = message;
		this.userdto = userdto;
	}
	public Error(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public Error() {
		super();
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserDto getUserdto() {
		return userdto;
	}
	public void setUserdto(UserDto userdto) {
		this.userdto = userdto;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
