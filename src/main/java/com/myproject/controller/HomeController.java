package com.myproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.dto.AppointmentDto;
import com.myproject.dto.ComboDto;
import com.myproject.dto.Error;
import com.myproject.dto.UserDto;
import com.myproject.service.ComboService;
import com.myproject.service.UserService;

@Component
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ComboService comSer;
	
	@Autowired
	private UserService userSer;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		UserDto user = new UserDto();
		AppointmentDto dtos = new AppointmentDto();
		List<ComboDto> combos = comSer.findAll();
		model.addAttribute("combos", combos);
		model.addAttribute("appointment", dtos);
		model.addAttribute("user", user);
		return "home/index";
	}
	
	@GetMapping(value = "/test")
	public String test() {
		return "test";
	}
	
	@PostMapping(value = "/user/login")
	public String login(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, RedirectAttributes re) {
		Error err = (Error) userSer.checkLogin(userDto);
		if(err.getStatus()==1) {
			re.addFlashAttribute("success", "Đăng nhập thành công");
			return "redirect:/service";
		}else if(err.getStatus() == 2) {
			re.addFlashAttribute("error", err.getMessage());
			return "redirect:/";
		}else {
			re.addFlashAttribute("notexists", err.getMessage());
			return "redirect:/";
		}
	}
}
