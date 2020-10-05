package com.myproject.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myproject.dto.AppointmentDto;
import com.myproject.entity.Appointment;
import com.myproject.reponsitory.AppointmentRepository;
import com.myproject.service.AppointmentService;

public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appRepsitory;
	
	@Override
	public void add(AppointmentDto appDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AppointmentDto> findAll() {
		List<Appointment> listEntity = appRepsitory.findAll();
		List<AppointmentDto> listDto = new ArrayList<AppointmentDto>();
		for(Appointment entity : listEntity) {
			listDto.add(new AppointmentDto(entity.getId(), 
					entity.getCode(), 
					entity.getDate(), 
					entity.getName(), 
					entity.getNameStylist(), 
					entity.getPhone(), 
					entity.getTime()));
		}
		return listDto;
	}

	public AppointmentDto entityChangeDto(Appointment entity) {
		AppointmentDto appDto = new AppointmentDto();
		appDto.setId(entity.getId());
		appDto.setCode(entity.getCode());
		appDto.setName(entity.getName());
		appDto.setNameStylist(entity.getNameStylist());
		appDto.setDate(entity.getDate());
		appDto.setPhone(entity.getPhone());
		appDto.setTime(entity.getTime());
		return appDto;
	}
	
	public Appointment dtoChangeEntity(AppointmentDto appDto) {
		Appointment entity = new Appointment();
		entity.setId(appDto.getId());
		entity.setCode(appDto.getCode());
		entity.setName(appDto.getName());
		entity.setNameStylist(appDto.getNameStylist());
		entity.setPhone(appDto.getPhone());
		entity.setTime(appDto.getTime());
		entity.setDate(appDto.getDate());
		return entity;
	}
}
