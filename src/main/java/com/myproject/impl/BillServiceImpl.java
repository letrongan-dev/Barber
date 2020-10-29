package com.myproject.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dto.BillDto;
import com.myproject.entity.Appointment;
import com.myproject.entity.Bill;
import com.myproject.reponsitory.AppointmentRepository;
import com.myproject.reponsitory.BillRepository;
import com.myproject.service.BillService;


@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepos;

	
	@Override
	public List<BillDto> findAll() {
		List<Bill> entities = (List<Bill>) billRepos.findAll();
		List<BillDto> dtos = new ArrayList<BillDto>();
		for( Bill b : entities) {
			dtos.add(entityChangeDto(b));
		}
		return dtos;
	}

	@Override
	public BillDto findById(int id) {
		Bill entity = billRepos.getOne(id);
		BillDto dto = entityChangeDto(entity);
		return dto;
	}

	@Override
	public void add(BillDto dto, int id) {
	}
	
	BillDto entityChangeDto (Bill entity) {
		BillDto dto = new BillDto();
		dto.setId(entity.getId());
		dto.setComboId(entity.getComboId());
		dto.setUserId(entity.getUserId());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getCreatedAt());
		dto.setTotal(entity.getTotal());
		dto.setNote(entity.getNote());
		dto.setNameCustomer(entity.getNameCustomer());
		dto.setPhone(entity.getPhone());
		dto.setAppointmentId(entity.getAppointmentId());
		dto.setStatus(entity.getStatus());
		return dto;
	}
	Bill dtoChangeEntity (BillDto dto) {
		Bill entity = new Bill();
		entity.setId(dto.getId());
		entity.setComboId(dto.getComboId());
		entity.setUpdatedAt(dto.getUpdatedAt());
		entity.setNote(dto.getNote());
		entity.setTotal(dto.getTotal());
		entity.setUserId(dto.getUserId());
		entity.setPhone(dto.getPhone());
		entity.setNameCustomer(dto.getNameCustomer());
		return entity;
	}
	Bill setPaymentBill (BillDto dto) {
		Bill entity = billRepos.getOne(dto.getId());
		entity.setStatus(dto.getStatus());
		entity.setTotal(dto.getTotal());
		return entity;
	}

	@Override
	public void update(BillDto dto) {
		Bill entity = setPaymentBill(dto);
		billRepos.save(entity);
	}

	@Override
	public int delete(int id) {
		List<Bill> billUnpaid = billRepos.billUnpaid(0);
		for (Bill entity : billUnpaid) {
			if(entity.getId() == id){
				billRepos.delete(entity);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public List<BillDto> listBillUnpaid() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
