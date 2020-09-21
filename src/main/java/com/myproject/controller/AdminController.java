package com.myproject.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = "/dashboard.html", method = RequestMethod.GET)
	public String index() {
		return "admin/index";
	}
}
