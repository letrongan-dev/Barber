package com.myproject.service;

import java.util.List;

import com.myproject.dto.AppointmentDto;


public interface AppointmentService {

	int add (AppointmentDto appDto);
	List<AppointmentDto> findAll();
	int delete (int id);
	AppointmentDto findById(int id);
	void edit (AppointmentDto appDto);
}
