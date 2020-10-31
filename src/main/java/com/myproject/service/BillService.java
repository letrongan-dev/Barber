package com.myproject.service;

import java.util.List;

import com.myproject.dto.BillDto;

public interface BillService {

	List<BillDto> findAll();
	List<BillDto> listBillUnpaid();
	List<BillDto> listBillByUserid(int userId);
	BillDto findById(int id);
	void add (BillDto dto, int id);
	void update(BillDto dto);
	int delete(int id);
}
