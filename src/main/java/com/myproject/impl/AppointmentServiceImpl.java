package com.myproject.impl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dto.AppointmentDto;
import com.myproject.entity.Appointment;
import com.myproject.reponsitory.AppointmentRepository;
import com.myproject.reponsitory.UserRepository;
import com.myproject.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appRepsitory;
	
	@Autowired
	private UserRepository userReps;
	
	@Override
	public int add(AppointmentDto appDto) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        
        Date date1 = appDto.getDate();
        Date date2 = new Date();

        c1.setTime(date1);
        c2.setTime(date2);

        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
//        System.err.print("Số ngày giữa " + dateFormat.format(c1.getTime())
//
//        + " và " + dateFormat.format(c2.getTime()) + ": ");
//
//        System.err.println(noDay);
        if(noDay != 0) {
    		return 1;
        }try {
        	Appointment entity = dtoChangeEntity(appDto);
    		appRepsitory.save(entity);
    		
        } catch (Exception e) {
			e.printStackTrace();
		} 
        return 0;
        
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
		appDto.setStylistId(entity.getUserId());
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
		entity.setUserId(appDto.getStylistId());
		return entity;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AppointmentDto findById(int id) {
		Appointment entity = appRepsitory.getOne(id);
		AppointmentDto dto = entityChangeDto(entity);
		return dto;
	}

	@Override
	public void edit(AppointmentDto appDto) {
		Appointment entity = appRepsitory.getOne(appDto.getId());
		entity.setName(appDto.getName());
		entity.setNameStylist(appDto.getNameStylist());
		entity.setUserId(appDto.getStylistId());
		entity.setPhone(appDto.getPhone());
		entity.setTime(appDto.getTime());
		entity.setDate(appDto.getDate());
		entity.setComboId(appDto.getComboId());
		entity.setMessage(appDto.getMessage());
		appRepsitory.save(entity);
	}
}
