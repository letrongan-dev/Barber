package com.myproject.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myproject.dto.UserDetailsDto;
import com.myproject.dto.UserDto;
import com.myproject.reponsitory.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto user = userRepository.findByEmailDto(username);
		if(user == null) throw new UsernameNotFoundException("Sai email!"); 
		//List danh sách role bắt buộc dù chỉ có 1 phần tử
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRoleName()));
		UserDetailsDto dto = new UserDetailsDto(user.getEmail(), user.getPassword(), authorities, user.getName(),user.getAvatar(), user.getPhone(), user.getAddress(), user.getEmail(), user.getCode());
		return dto;
	}

}
