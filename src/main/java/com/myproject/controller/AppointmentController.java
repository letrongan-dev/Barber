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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.dto.AppointmentDto;
import com.myproject.dto.ComboDto;
import com.myproject.dto.UserDto;
import com.myproject.service.AppointmentService;
import com.myproject.service.ComboService;
import com.myproject.service.UserService;

@Controller
@RequestMapping("/")
public class AppointmentController {

	@Autowired
	private UserService userServ;
	
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
		int result = appServ.add(dto);
		if(bindingResult.hasErrors()) {
			List<ComboDto> combos = comSer.findAll();
			model.addAttribute("combos", combos);
			return "home/service";
		}else if(result == 1) {
			model.addAttribute("warning", "Quý khách chỉ được đặt trước 1 ngày!");
			List<ComboDto> combos = comSer.findAll();
			model.addAttribute("combos", combos);
			return "home/service";
		}else {
			re.addFlashAttribute("success", "Đặt lịch thành công! Xin quí khách đến trước 15 phút");
			return "redirect:/";
		}
		
	}
	@GetMapping(value = "/admin/appointment/update-stylist")
	public String edit(@RequestParam("id") int id, ModelMap model) {
		AppointmentDto dto = appServ.findById(id);
		List<UserDto> stylists = userServ.listStylist();
		List<ComboDto> combos = comSer.findAll();
		if(dto==null) {
			model.addAttribute("empty", "Không tìm thấy lịch hẹn");
			return "appointment/index";
		}else {
			model.addAttribute("app", dto);
			model.addAttribute("combos", combos);
			model.addAttribute("stylists", stylists);
			return "appointment/update";
		}
	}
	@PostMapping(value = "/admin/appointment/update")
	public String update(@ModelAttribute("app") AppointmentDto appDto, ModelMap model, RedirectAttributes re) {
		List<UserDto> stylists = userServ.listStylist();
		List<ComboDto> combos = comSer.findAll();
		
		if(appDto.getStylistId() == 0 || appDto.getStylistId() <0) {
			model.addAttribute("error", "Vui lòng chọn stylist");
			model.addAttribute("combos", combos);
			model.addAttribute("stylists", stylists);
			return "appointment/update";
		}else {
			AppointmentDto dto = appServ.findById(appDto.getId()); 
			for(UserDto user : stylists) {
				if(appDto.getStylistId() == user.getId()) {
					appDto.setNameStylist(user.getName());
					appDto.setComboId(dto.getComboId());
					appServ.edit(appDto);
					re.addFlashAttribute("success", "Cập nhật thành công!");
				}
			}
			return "redirect:/admin/appointment";
		}
	}

}
