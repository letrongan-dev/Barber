package com.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppointmentController {

	@GetMapping(value = "/service")
	public String index() {
		return "home/service";
	}
	
	@PostMapping(value = "/appointment")
	public String add() {
		return "redirect:/service";
	}

}
