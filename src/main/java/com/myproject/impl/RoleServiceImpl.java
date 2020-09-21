package com.myproject.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myproject.dto.RoleDto;
import com.myproject.entity.Role;
import com.myproject.reponsitory.RoleRepository;
import com.myproject.service.RoleService;

public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<RoleDto> getAll() {
		List<RoleDto> roleDtos = new ArrayList<RoleDto>();
		List<Role> roles = roleRepository.findAll();
		for(Role role : roles) {
			roleDtos.add(new RoleDto(
					role.getId(),
					role.getName(),
					role.getDescription()
					));
		}
		return roleDtos;
	}

	@Override
	public void add(RoleDto roleDto) {
		Role entity = new Role(); 
		entity.setName(roleDto.getName());
		entity.setDescription(roleDto.getDesc());
		roleRepository.save(entity);
	}

}
