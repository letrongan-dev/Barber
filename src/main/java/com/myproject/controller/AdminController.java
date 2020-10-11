package com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myproject.dto.ShowDetailsUserAuth;
import com.myproject.dto.UserDto;
import com.myproject.reponsitory.UserRepository;

@Component
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String index() {
		return "admin/index";
	}
}
