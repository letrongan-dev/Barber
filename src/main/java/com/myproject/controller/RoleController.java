package com.myproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.dto.RoleDto;
import com.myproject.service.RoleService;

@Component
@RequestMapping("/admin")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/role" , method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<RoleDto> rolesDto = roleService.getAll();
		model.addAttribute("roles", rolesDto);
		return "role/index";
	}
	@RequestMapping(value = "/role/add",method = RequestMethod.GET)
	public String add(ModelMap model) {
		RoleDto role = new RoleDto();
		model.addAttribute("role", role);
		return "role/add";
	}
	@PostMapping(value = "/role/add")
	public String add(@Valid @ModelAttribute("role") RoleDto entity, BindingResult bindingResult, ModelMap model ) {
		if(bindingResult.hasErrors()) {
			return "role/add";
		}else {
			roleService.add(entity);
			model.addAttribute("success", "Thêm thành công !!");
			return "redirect:/admin/role";
		}
	}
	@GetMapping(value = "/role/edit")
	public String edit(@RequestParam("id") int id, ModelMap model) {
		RoleDto roleDto = roleService.findById(id);
		model.addAttribute("role", roleDto);
		return "role/edit";
	}
	@PostMapping(value = "/role/edit")
	public String edit(@Valid @ModelAttribute("role") RoleDto entity, BindingResult bindingResult, ModelMap model) {
		if(bindingResult.hasErrors()) {
			return "role/edit";
		}else {
			roleService.update(entity);
			model.addAttribute("success", "Cập nhật thành công !!");
			return "redirect:/admin/role";
		}
	}
	
	@GetMapping(value = "role/delete")
	public String delete(@RequestParam("id") int id, ModelMap model) {
		int result = roleService.delete(id);
		if(result==1) {
			model.addAttribute("success", "Xoá thành công");
		}else {
			model.addAttribute("error", "Xoá thất bại");
		}
		return "redirect:/admin/role";
	}
	
}

