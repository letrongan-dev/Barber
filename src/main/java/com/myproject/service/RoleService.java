package com.myproject.service;

import java.util.List;


import com.myproject.dto.RoleDto;

public interface RoleService {
	List<RoleDto> getAll();
	
	void add(RoleDto roleDto);
	
	void update(RoleDto roleDto);
	
	RoleDto findById(int id);
	
	int delete(int id);
}
