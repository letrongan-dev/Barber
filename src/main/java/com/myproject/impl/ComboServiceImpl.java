package com.myproject.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dto.ComboDto;
import com.myproject.entity.Combo;
import com.myproject.reponsitory.ComboRepository;
import com.myproject.service.ComboService;

@Service
public class ComboServiceImpl implements ComboService {

	@Autowired
	private ComboRepository comboRes;
	
	@Override
	public List<ComboDto> findAll() {
		List<Combo> entity = comboRes.findAll();
		List<ComboDto> dtos = new ArrayList<ComboDto>();
		
		for(Combo cb : entity) {
			dtos.add(entityChangeDto(cb));
		}
		return dtos;
	}

	@Override
	public void add(ComboDto comboDto) {
		Combo entity = dtoChangeEntity(comboDto);
		comboRes.save(entity);
	}
	
	public ComboDto entityChangeDto(Combo entity) {
		ComboDto dto = new ComboDto(entity.getId()
				, entity.getName()
				, entity.getPrice()
				, entity.getStep1()
				, entity.getStep2()
				, entity.getStep3()
				, entity.getStep4()
				, entity.getStep5());
		return dto;
	}
	
	public Combo dtoChangeEntity(ComboDto dto) {
		Combo entity = new Combo(dto.getId()
				, dto.getName()
				, dto.getPrice()
				, dto.getStep1(), dto.getStep2(), dto.getStep3(), dto.getStep4(), dto.getStep5());
		return entity;
	}

	@Override
	public int delete(int id) {
		Combo entity = comboRes.getOne(id);
		if(entity == null) {
			return 1;
		}else {
			comboRes.delete(entity);
			return 0;
		}
		
	}

	@Override
	public void update(ComboDto comboDto) {
		Combo entity =  comboRes.getOne(comboDto.getId());
		entity.setId(comboDto.getId());
		entity.setName(comboDto.getName());
		entity.setPrice(comboDto.getPrice());
		entity.setStep1(comboDto.getStep1());
		entity.setStep2(comboDto.getStep2());
		entity.setStep3(comboDto.getStep3());
		entity.setStep4(comboDto.getStep4());
		entity.setStep5(comboDto.getStep5());
		comboRes.save(entity);
	}

	@Override
	public ComboDto findById(int id) {
		Combo entity = comboRes.getOne(id);
		ComboDto dto = entityChangeDto(entity);
		return dto;
	}

}
