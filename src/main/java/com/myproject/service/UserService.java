package com.myproject.service;



import java.util.List;

import com.myproject.dto.AuthChangePasswordDto;
import com.myproject.dto.UserDto;
import com.myproject.dto.UserDtoUpdate;
import com.myproject.entity.User;

public interface UserService {
	List<UserDto> findAll();
	void add(UserDto userDto);
	void edit(UserDtoUpdate userDtoUpdate);
	int delete(int id);
	//UserDtoUpdate findDtoCode(String code);
	UserDtoUpdate findById(int id);
	String hashPassword(String password);
	String generateRandom(int len);
	int checkExist(String email);
	List<UserDto> listStylist();
	int changePass (AuthChangePasswordDto dto);
	void addCustomer (UserDto dto);
}
