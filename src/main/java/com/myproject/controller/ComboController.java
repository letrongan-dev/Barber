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

import com.myproject.dto.ComboDto;
import com.myproject.service.ComboService;

@Controller
@RequestMapping(value = "/admin")
public class ComboController {

	@Autowired
	private ComboService comboServ;
	
	@GetMapping(value = "/combo")
	public String index(ModelMap model) {
		List<ComboDto> dtos = comboServ.findAll();
		model.addAttribute("combos", dtos);
		return "combo/index";
	}
	
	@GetMapping(value = "/combo/add")
	public String add(ModelMap model) {
		ComboDto combo = new ComboDto();
		model.addAttribute("combo", combo);
		return "combo/add";
	}
	
	@PostMapping(value = "/combo/add")
	public String add(@Valid @ModelAttribute("combo") ComboDto dto, BindingResult bindingResult, RedirectAttributes re) {
		if(bindingResult.hasErrors()) {
			return "combo/add";
		}else {
			comboServ.add(dto);
			re.addFlashAttribute("success", "Thêm thành công !!");
			return "redirect:/admin/combo";
		}
	}
	@GetMapping(value = "/combo/delete")
	public String delete(@RequestParam("id") int id, RedirectAttributes re, ModelMap model) {
		int result = comboServ.delete(id);
		if(result==1) {
			model.addAttribute("empty", "Không tìm thấy !!");
			return "combo/index";
		}else {
			re.addFlashAttribute("succes", "Xóa thành công");
			return "redirect:/admin/combo";
		}
	}
	@GetMapping(value = "/combo/edit")
	public String edit(@RequestParam("id") int id, ModelMap model) {
		ComboDto dto = comboServ.findById(id);
		model.addAttribute("combo", dto);
		return "combo/edit";
	}
	@PostMapping(value = "/combo/edit")
	public String update(@Valid @ModelAttribute("combo")ComboDto comboDto, BindingResult bindingResult, ModelMap model, RedirectAttributes re) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("error", "Update thất bại!!");
			return "combo/edit";
		}else {
			comboServ.update(comboDto);
			re.addFlashAttribute("success", "Update thành công !!");
			return "redirect:/admin/combo";
		}
	}
}
