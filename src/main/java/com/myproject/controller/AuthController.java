package com.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class AuthController {

	@GetMapping(value = "/login")
	public String index(@RequestParam(value = "error" , defaultValue = "", required = false) String error , ModelMap model) {
		if(!error.isEmpty()) {
			model.addAttribute("error", "Sai thông tin đăng nhập");
		}		
		return "login";
	}
}