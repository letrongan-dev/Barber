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

import com.myproject.dto.AppointmentDto;
import com.myproject.dto.AuthChangePasswordDto;
import com.myproject.dto.BillDto;
import com.myproject.dto.ComboDto;
import com.myproject.dto.RoleDto;
import com.myproject.dto.UserDto;
import com.myproject.reponsitory.BillRepository;
import com.myproject.reponsitory.UserRepository;
import com.myproject.service.AppointmentService;
import com.myproject.service.BillService;
import com.myproject.service.ComboService;
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
	
	@Autowired
	private AppointmentService AppSer;

	@Autowired
	private ComboService comboSer;
	
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
		List<AppointmentDto> listApp = AppSer.listAppByPhone(user.getPhone());
		long billOfUser = billRep.countBillUserId(1, user.getId());
		List<RoleDto> roleDto = roleSer.getAll();
		List<ComboDto> combos = comboSer.findAll();
		model.addAttribute("authChange", authChange);
		model.addAttribute("user", user);
		model.addAttribute("roles", roleDto);
		model.addAttribute("quanty", billOfUser);
		model.addAttribute("history", listApp);
		model.addAttribute("combos", combos);
		return "user/profile";
	}
	@PostMapping(value = "/auth/change-password")
	public String changePass(@Valid @ModelAttribute("authChange")AuthChangePasswordDto dto, BindingResult bindingResult, RedirectAttributes re, ModelMap model,
			@RequestParam("rePassword")String rePassword, @RequestParam("code")String code) {
		dto.setCode(code);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		UserDto user = userReps.findByEmailDto(username);
		AuthChangePasswordDto authChange = new AuthChangePasswordDto();
		int result = userSer.changePass(dto);
		String linkReturn = null;
		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("authChange", authChange);
			model.addAttribute("user", user);
			switch (user.getRoleId()) {
			case 1:
				linkReturn = "user/adminProfile";
				break;
			case 2:
				linkReturn = "user/adminProfile";
				break;
			default:
				linkReturn = "user/profile";
				break;
			}
			return linkReturn;
			
		}else if(!dto.getNewPassword().equals(rePassword)){
			switch (user.getRoleId()) {
			case 1:
				linkReturn = "redirect:/admin/profile";
				break;
			case 2:
				linkReturn = "redirect:/admin/profile";
				break;
			default:
				linkReturn = "redirect:/profile";
				break;
			}
			re.addFlashAttribute("errorRe", "Mật khẩu nhập lại không đúng!");
			return linkReturn;
			
		}else if(result == 1){
			switch (user.getRoleId()) {
			case 1:
				linkReturn = "redirect:/admin/profile";
				break;
			case 2:
				linkReturn = "redirect:/admin/profile";
				break;
			default:
				linkReturn = "redirect:/profile";
				break;
			}
			re.addFlashAttribute("errorOld", "Mật khẩu cũ không đúng!");
			return linkReturn;
		}else if(result == 0){
			switch (user.getRoleId()) {
			case 1:
				linkReturn = "redirect:/admin/profile";
				break;
			case 2:
				linkReturn = "redirect:/admin/profile";
				break;
			default:
				linkReturn = "redirect:/profile";
				break;
			}
			re.addFlashAttribute("success", "Cập nhật thành công");
			return linkReturn;
		}else {
			switch (user.getRoleId()) {
			case 1:
				linkReturn = "redirect:/admin/profile";
				break;
			case 2:
				linkReturn = "redirect:/admin/profile";
				break;
			default:
				linkReturn = "redirect:/profile";
				break;
			}
			re.addFlashAttribute("error", "Hệ thống bị lỗi!");
			return linkReturn;
			
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
