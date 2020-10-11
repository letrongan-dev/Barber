package com.myproject.service;

import java.util.List;

import com.myproject.dto.ComboDto;

public interface ComboService {

	List<ComboDto> findAll();
	void add(ComboDto comboDto);
	int delete(int id);
	void update(ComboDto comboDto);
	ComboDto findById(int id);
}
