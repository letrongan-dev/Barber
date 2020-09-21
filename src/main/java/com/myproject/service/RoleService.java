package com.myproject.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.myproject.dto.RoleDto;

@Component
public interface RoleService {
	List<RoleDto> getAll();
	
	void add(RoleDto roleDto);
	
}
