package com.myproject.controller;

import javax.validation.Valid;

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

import com.myproject.dto.UserDto;

@Component
@RequestMapping("/")
public class HomeController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
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
