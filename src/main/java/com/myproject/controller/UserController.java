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

import com.myproject.dto.RoleDto;
import com.myproject.dto.UserDto;
import com.myproject.dto.UserDtoUpdate;
import com.myproject.entity.MyFile;
import com.myproject.service.FileService;
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
	
//	@Autowired
//	private FileService fileService;
	
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
		MyFile myFile = new MyFile();
		List<RoleDto> roles = roleService.getAll();
		userDto.setCode(userService.generateRandom(10));
		model.addAttribute("user", userDto);
		model.addAttribute("roles", roles);
		model.addAttribute("myfile", myFile);
		return "user/add";
	}
//	@PostMapping(value = "/user/add")
//	public String add(@Valid @ModelAttribute("user") UserDto userDto, 
//			HttpServletRequest request,
//			@ModelAttribute("myfile") MyFile myFile, 
//			BindingResult bindingResult) {
//		fileService.uploadFile(myFile,request);
//		userDto.setAvatar(myFile.getMultipartFile().getOriginalFilename());
//		userService.add(userDto);
//		return "redirect:/admin/user.html";
//	}
	@PostMapping("/users/save")
    public String saveUser(UserDto userDto,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        userDto.setAvatar(fileName);
         
        userService.add(userDto);
 
        String uploadDir = "user-photos/";
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
         
        return "redirect:/admin/user";
    }
	
	@GetMapping(value = "/user/delete")
	public String delete(@RequestParam("id") int id, ModelMap model) {
		int result = userService.delete(id);
		if(result == 1) {
			model.addAttribute("success", "Xóa thành công !");
		}else {
			model.addAttribute("error", "Xóa thất bại!");
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
    public String updateUser(UserDtoUpdate userDtoUpdate
    		,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(!fileName.isEmpty()) {
        	String uploadDir = "/user-photos/";
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	    	userDtoUpdate.setAvatar(fileName);
	        userService.edit(userDtoUpdate);
        }
        return "redirect:/admin/user";
    }
}
	
	


