package com.myproject.impl;




import java.util.ArrayList;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myproject.dto.AuthChangePasswordDto;
import com.myproject.dto.Error;
import com.myproject.dto.UserDto;
import com.myproject.dto.UserDtoUpdate;
import com.myproject.entity.User;
import com.myproject.reponsitory.UserRepository;
import java.util.Random;


import com.myproject.service.UserService;




@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserDto> findAll() {
		List<User> users = userRepository.findAll();
		List<UserDto> listUserDto = new ArrayList<UserDto>();
		for(User user : users) {
			listUserDto.add(new UserDto(
					user.getId(),
					user.getCode(),
					user.getName(),
					user.getPhone(),
					user.getEmail(),
					user.getPassword(),
					user.getAddress(),
					user.getRoleId(),
					user.getAvatar()
					));
		}
		return listUserDto;
	}
	
	
	@Override
	public void add(UserDto userDto) {
		User entity = dtoChangeToEntity(userDto);
		entity.setPassword(hashPassword(userDto.getPassword()));
		userRepository.save(entity);
	}

	@Override
	public void edit(UserDtoUpdate userDtoUpdate) {
		User entity = userRepository.getOne(userDtoUpdate.getId());
		entity.setId(userDtoUpdate.getId());
		entity.setCode(userDtoUpdate.getCode());
		entity.setName(userDtoUpdate.getName());
		entity.setEmail(userDtoUpdate.getEmail());
		entity.setPhone(userDtoUpdate.getPhone());
		entity.setRoleId(userDtoUpdate.getRoleId());
		entity.setAvatar(userDtoUpdate.getAvatar());
		entity.setAddress(userDtoUpdate.getAddress());
		userRepository.save(entity);
		
	}

	@Override
	public UserDtoUpdate findById(int id) {
		User entity = userRepository.getOne(id);
		UserDtoUpdate userDtoUpdate = new UserDtoUpdate(entity.getId(), entity.getCode(), entity.getName(), entity.getPhone(), entity.getEmail(), entity.getAddress(), entity.getRoleId(), entity.getAvatar());
		
		return userDtoUpdate;
	}

	@Override
	public String hashPassword(String password) {
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
		return hashed;
	}

	@Override
	public String generateRandom(int len) {
		String chars = "0123456789114168615148465184648631864658135465313541351654665848465313216846584";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return "HC" + sb.toString();
	}
	
	public User dtoChangeToEntity(UserDto userDto) {
		User entity = new User();
		entity.setId(userDto.getId());
		entity.setCode(userDto.getCode());
		entity.setName(userDto.getName());
		entity.setPhone(userDto.getPhone());
		entity.setEmail(userDto.getEmail());
		entity.setPassword(userDto.getPassword());
		entity.setRoleId(userDto.getRoleId());
		entity.setAvatar(userDto.getAvatar());
		entity.setAddress(userDto.getAddress());
		return entity;
	}	
	public UserDto entityChangeToDto(User entity) {
		UserDto userDto = new UserDto();
		userDto.setId(entity.getId());
		userDto.setCode(entity.getCode());
		userDto.setName(entity.getName());
		userDto.setEmail(entity.getEmail());
		userDto.setPassword(entity.getPassword());
		userDto.setPhone(entity.getPhone());
		userDto.setAddress(entity.getAddress());
		userDto.setAvatar(entity.getAvatar());
		userDto.setRoleId(entity.getRoleId());
		return userDto;
	}


	@Override
	public int delete(int id) {
		User entity = userRepository.getOne(id);
		if(entity!=null) {
			userRepository.delete(entity);
			return 1;
		}else {
			return 0;
		}
	}
	
	@Override
	public int checkExist(String email) {
		int result = userRepository.countByEmail(email);
		if(result>0) {
			return 1;
		}else {
			return 0;
		}
	}


	@Override
	public List<UserDto> listStylist() {
		List<User> list = userRepository.listStylist(3);
		List<UserDto> stylist = new ArrayList<UserDto>();
		for(User u : list) {
			stylist.add(entityChangeToDto(u));
		}
		return stylist;
	}


	@Override
	public int changePass(AuthChangePasswordDto dto) {
		User entity = userRepository.findByCode(dto.getCode());
		boolean checked = BCrypt.checkpw(dto.getOldPassword(), entity.getPassword());
		if(!checked) {
			return 1;
		}else {
			entity.setPassword(BCrypt.hashpw(dto.getNewPassword(), BCrypt.gensalt(12)));
			userRepository.save(entity);
			return 0;
		}
	}


	@Override
	public void addCustomer(UserDto dto) {
		User entity = new User();
		entity.setCode("CUS - " + generateRandom(5));
		entity.setAvatar(null);
		entity.setAddress(null);
		entity.setEmail(dto.getEmail());
		entity.setPassword(hashPassword(dto.getPassword()));
		entity.setPhone(dto.getPhone());
		entity.setRoleId(4);
		entity.setName(dto.getName());
		entity.setAvatar("default-avatar.png");
		userRepository.save(entity);
		
	}
}
