package com.myproject.service;

import java.util.List;

import com.myproject.dto.AppointmentDto;


public interface AppointmentService {

	void add (AppointmentDto appDto);
	List<AppointmentDto> findAll();
	
	
}
