package com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




import com.myproject.reponsitory.UserRepository;

@Component
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserRepository userR;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "admin/index";
	}
}
