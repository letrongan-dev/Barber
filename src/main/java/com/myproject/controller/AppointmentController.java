package com.myproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.dto.AppointmentDto;
import com.myproject.dto.ComboDto;
import com.myproject.service.AppointmentService;
import com.myproject.service.ComboService;

@Controller
@RequestMapping("/")
public class AppointmentController {

	@Autowired
	private AppointmentService appServ;
	
	@Autowired
	private ComboService comSer;
	
	@GetMapping(value = "/service")
	public String service(ModelMap model) {
		AppointmentDto dtos = new AppointmentDto();
		List<ComboDto> combos = comSer.findAll();
		model.addAttribute("appointment", dtos);
		model.addAttribute("combos", combos);
		return "home/service";
	}
	
	@GetMapping(value = "/admin/appointment")
	public String listApp(ModelMap model) {
		List<AppointmentDto> dtos = appServ.findAll();
		List<ComboDto> combos = comSer.findAll();
		model.addAttribute("apps", dtos);
		model.addAttribute("combos", combos);
		return "appointment/index";
	}
	
	@PostMapping(value = "/appointment")
	public String add(@Valid @ModelAttribute("appointment") AppointmentDto dto, BindingResult bindingResult, ModelMap model) {
		if(bindingResult.hasErrors()) {
			return "home/service";
		}else {
			appServ.add(dto);
			model.addAttribute("success", "Đặt lịch thành công!");
			return "redirect:/service";
		}
		
	}

}
