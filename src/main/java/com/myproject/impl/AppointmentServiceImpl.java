package com.myproject.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dto.AppointmentDto;
import com.myproject.entity.Appointment;
import com.myproject.reponsitory.AppointmentRepository;
import com.myproject.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appRepsitory;
	
	@Override
	public void add(AppointmentDto appDto) {
		Appointment entity = dtoChangeEntity(appDto);
		appRepsitory.save(entity);
	}

	@Override
	public List<AppointmentDto> findAll() {
		List<Appointment> listEntity = appRepsitory.findAll();
		List<AppointmentDto> listDto = new ArrayList<AppointmentDto>();
		for(Appointment entity : listEntity) {
			listDto.add(entityChangeDto(entity));
		}
		return listDto;
	}

	public AppointmentDto entityChangeDto(Appointment entity) {
		AppointmentDto appDto = new AppointmentDto();
		appDto.setId(entity.getId());
		appDto.setName(entity.getName());
		appDto.setNameStylist(entity.getNameStylist());
		appDto.setDate(entity.getDate());
		appDto.setPhone(entity.getPhone());
		appDto.setTime(entity.getTime());
		appDto.setMessage(entity.getMessage());
		appDto.setComboId(entity.getComboId());
		return appDto;
	}
	
	public Appointment dtoChangeEntity(AppointmentDto appDto) {
		Appointment entity = new Appointment();
		entity.setName(appDto.getName());
		entity.setNameStylist(appDto.getNameStylist());
		entity.setPhone(appDto.getPhone());
		entity.setTime(appDto.getTime());
		entity.setDate(appDto.getDate());
		entity.setComboId(appDto.getComboId());
		entity.setMessage(appDto.getMessage());
		return entity;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AppointmentDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(AppointmentDto appDto) {
		// TODO Auto-generated method stub
		
	}
}
