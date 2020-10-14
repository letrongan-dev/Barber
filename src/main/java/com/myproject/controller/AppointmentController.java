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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping(value = "/admin/appointment")
	public String listApp(ModelMap model) {
		List<AppointmentDto> dtos = appServ.findAll();
		List<ComboDto> combos = comSer.findAll();
		model.addAttribute("apps", dtos);
		model.addAttribute("combos", combos);
		return "appointment/index";
	}
		
	@GetMapping(value = "/service")
	public String service(ModelMap model) {
		AppointmentDto dtos = new AppointmentDto();
		List<ComboDto> combos = comSer.findAll();
		model.addAttribute("appointment", dtos);
		model.addAttribute("combos", combos);
		return "home/service";
	}
		
	@PostMapping(value = "/appointment")
	public String add(@Valid @ModelAttribute("appointment") AppointmentDto dto, BindingResult bindingResult, ModelMap model, RedirectAttributes re) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("error", "Đặt lịch thất bại");
			return "home/service";
		}else {
			appServ.add(dto);
			re.addFlashAttribute("success", "Đặt lịch thành công!");
			return "redirect:/";
		}
		
	}

}
