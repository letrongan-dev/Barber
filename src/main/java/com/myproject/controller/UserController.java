package com.myproject.controller;


import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.dto.RoleDto;
import com.myproject.dto.UserDto;
import com.myproject.dto.UserDtoUpdate;
import com.myproject.service.RoleService;
import com.myproject.service.UserService;
import com.myproject.util.FileUploadUtil;


@Component
@RequestMapping(value = "/admin")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	
	@GetMapping(value = "/user")
	public String index(ModelMap model) {
		List<UserDto> userDtos = userService.findAll();
		List<RoleDto> roleDtos = roleService.getAll();
		model.addAttribute("users", userDtos);
		model.addAttribute("roles", roleDtos);
		return "user/index";
	}
	@GetMapping(value = "/user/add")
	public String add(ModelMap model) {
		UserDto userDto = new UserDto();
		List<RoleDto> roles = roleService.getAll();
		userDto.setCode(userService.generateRandom(10));
		model.addAttribute("user", userDto);
		model.addAttribute("roles", roles);
		return "user/add";
	}
	
	@PostMapping("/users/save")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult,
            @RequestParam("image") MultipartFile multipartFile,  RedirectAttributes re, ModelMap model) throws IOException {
        int result = userService.checkExist(userDto.getEmail());
		if(bindingResult.hasErrors() && multipartFile.isEmpty()) {
			List<RoleDto> roles = roleService.getAll();
			model.addAttribute("roles", roles);
			return "user/add";
		}else if(result>0) {
			List<RoleDto> roles = roleService.getAll();
			model.addAttribute("roles", roles);
			model.addAttribute("warning", "Email này đã được đăng ký!!");
			return "user/add";
		}
		else {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        userDto.setAvatar(fileName);
         
        userService.add(userDto);
 
        String uploadDir = "upload/user";
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        re.addFlashAttribute("success", "Thêm thành công!");
        return "redirect:/admin/user";
		}
    }
	
	@GetMapping(value = "/user/delete")
	public String delete(@RequestParam("id") int id, RedirectAttributes model) {
		int result = userService.delete(id);
		if(result == 1) {
			model.addFlashAttribute("success", "Xóa thành công !");
		}else {
			model.addFlashAttribute("error", "Xóa thất bại!");
		}
		return "redirect:/admin/user";
	}
	
	@GetMapping(value = "/user/edit")
	public String edit(@RequestParam("id") int id, ModelMap model) {
		UserDtoUpdate dto = userService.findById(id);
		List<RoleDto> rolesDto = roleService.getAll();
		model.addAttribute("user", dto);
		model.addAttribute("roles", rolesDto);
		return "user/edit";
	}
	
	@PostMapping("/users/edit")
    public String updateUser(@Valid @ModelAttribute("user") UserDtoUpdate userDtoUpdate, BindingResult bindingResult,
            @RequestParam("image") MultipartFile multipartFile,  RedirectAttributes re, ModelMap model) throws IOException {
        if(bindingResult.hasErrors() && multipartFile.isEmpty()) {
        	List<RoleDto> roles = roleService.getAll();
			model.addAttribute("roles", roles);
			return "user/edit";
        } else if(multipartFile.getSize()!=0) {
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            userDtoUpdate.setAvatar(fileName); 
            userService.edit(userDtoUpdate);
            String uploadDir = "upload/user";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            re.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:/admin/user";
        }else {
        	userDtoUpdate.setAvatar(userService.findById(userDtoUpdate.getId()).getAvatar());
        	userService.edit(userDtoUpdate);
        	re.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:/admin/user";
        } 
    }
}
	
	


