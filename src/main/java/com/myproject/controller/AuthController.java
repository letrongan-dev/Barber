package com.myproject.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.dto.AuthChangePasswordDto;
import com.myproject.dto.RoleDto;
import com.myproject.dto.UserDto;
import com.myproject.reponsitory.BillRepository;
import com.myproject.reponsitory.UserRepository;
import com.myproject.service.RoleService;
import com.myproject.service.UserService;

@Controller
@RequestMapping("")
public class AuthController {
	
	@Autowired
	private UserRepository userReps;
	
	@Autowired
	private UserService userSer;
	
	@Autowired
	private RoleService roleSer;
	
	@Autowired
	private BillRepository billRep;

	@GetMapping(value = "/login")
	public String index(@RequestParam(value = "error" , defaultValue = "", required = false) String error , ModelMap model) {
		if(!error.isEmpty()) {
			model.addAttribute("error", "Sai thông tin đăng nhập");
		}		
		return "login";
	}
	@GetMapping(value = "/profile")
	public String profile(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		UserDto user = userReps.findByEmailDto(username);
		AuthChangePasswordDto authChange = new AuthChangePasswordDto();
		long billOfUser = billRep.countBillUserId(1, user.getId());
		List<RoleDto> roleDto = roleSer.getAll();
		model.addAttribute("authChange", authChange);
		model.addAttribute("user", user);
		model.addAttribute("roles", roleDto);
		model.addAttribute("quanty", billOfUser);
		return "user/profile";
	}
	@PostMapping(value = "/auth/change-password")
	public String changePass(@Valid @ModelAttribute("authChange")AuthChangePasswordDto dto, BindingResult bindingResult, RedirectAttributes re, ModelMap model,
			@RequestParam("rePassword")String rePassword, @RequestParam("id") int id) {
		dto.setId(id);
		int result = userSer.changePass(dto);
		if(bindingResult.hasErrors()) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = ((UserDetails) principal).getUsername();
			UserDto user = userReps.findByEmailDto(username);
			AuthChangePasswordDto authChange = new AuthChangePasswordDto();
			long billOfUser = billRep.countBillUserId(1, user.getId());
			List<RoleDto> roleDto = roleSer.getAll();
			model.addAttribute("authChange", authChange);
			model.addAttribute("user", user);
			model.addAttribute("roles", roleDto);
			model.addAttribute("quanty", billOfUser);
			return "user/profile";
		}else if(!dto.getNewPassword().equals(rePassword)){
			re.addFlashAttribute("errorRe", "Mật khẩu nhập lại không đúng!");
			return "redirect:/profile";
		}else if(result == 1){
			re.addFlashAttribute("errorOld", "Mật khẩu cũ không đúng!");
			return "redirect:/profile";
		}else if(result == 0){
			re.addFlashAttribute("success", "Cập nhật thành công!");
			return "redirect:/profile";
		}else {
			re.addFlashAttribute("error", "Hệ thống bị lỗi!");
			return "redirect:/profile";
			
		}
	}
	@PostMapping(value = "/user/register")
	public String register(@RequestParam("name")String name,@RequestParam("email")String email, @RequestParam("password") String password,
			@RequestParam("phone")String phone, RedirectAttributes re) {
			int checkExists = userSer.checkExist(email);
			if(checkExists>0) {
				re.addFlashAttribute("error", "Email đã được đăng ký");
				return "redirect:/";
			}else {
				UserDto dto = new UserDto();
				dto.setEmail(email);
				dto.setPassword(password);
				dto.setName(name);
				dto.setPhone(phone);
				userSer.addCustomer(dto);
				re.addFlashAttribute("success", "Đăng ký thành công!");
				return "redirect:/";
			}	
	}
}
