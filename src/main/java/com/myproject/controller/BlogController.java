package com.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.myproject.dto.BlogDto;
import com.myproject.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogServ;
	
	@GetMapping(value = "/admin/blog")
	public String listBlog(ModelMap model) {
		List<BlogDto> dtos = blogServ.findAll();
		model.addAttribute("blogs", dtos);
		return "blog/index";
	}
	
	
}
