package com.myproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.dto.AppointmentDto;
import com.myproject.dto.ComboDto;
import com.myproject.dto.UserDto;
import com.myproject.service.ComboService;

@Component
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ComboService comSer;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		AppointmentDto dtos = new AppointmentDto();
		List<ComboDto> combos = comSer.findAll();
		model.addAttribute("combos", combos);
		model.addAttribute("appointment", dtos);
		return "home/index";
	}
	
	@GetMapping(value = "/test")
	public String test() {
		return "test";
	}
	
//	@PostMapping(value = "/login")
//	public String login(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, ModelMap modelMap ) {
//		return null;
//		
//	}
}
