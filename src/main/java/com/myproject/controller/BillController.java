package com.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.myproject.dto.BillDto;
import com.myproject.dto.ComboDto;
import com.myproject.dto.UserDto;
import com.myproject.service.BillService;
import com.myproject.service.ComboService;
import com.myproject.service.UserService;

@Controller
public class BillController {
	
	@Autowired
	private BillService billSer;
	
	@Autowired
	private ComboService comboSer;
	
	@Autowired
	private UserService userSer;
	
	@GetMapping(value="/admin/bill")
	public String listBill(ModelMap model) {
		List<BillDto> dtos = billSer.findAll();
		List<UserDto> stylists = userSer.listStylist();
		List<ComboDto> combos = comboSer.findAll();
		model.addAttribute("bills", dtos);
		model.addAttribute("combos", combos);
		model.addAttribute("stylists", stylists);
		return "bill/list";
	}
	
	@GetMapping(value = "/admin/bill-detail")
	public String getBill(@RequestParam("id")int id,ModelMap model) {
		BillDto dto = billSer.findById(id);
		List<UserDto> stylists = userSer.listStylist();
		List<ComboDto> combos = comboSer.findAll();
		model.addAttribute("combos", combos);
		model.addAttribute("stylists", stylists);
		model.addAttribute("bill", dto);
		return "bill/index";
	}
	
	@PostMapping(value = "/admin/payment")
	public String payment(@ModelAttribute("bill")BillDto dto, @RequestParam("id")int idApp, RedirectAttributes re) {
		dto.setStatus(1);
		billSer.update(dto);
		re.addFlashAttribute("success", "Thanh toán thành công");
		return "redirect:/admin/bill";
	}
	
	@GetMapping(value = "/admin/bill/delete")
	public String delete(@RequestParam("id")int id, RedirectAttributes re) {
		int result = billSer.delete(id);
		if(result == 0) {
			re.addFlashAttribute("error", "Lỗi không thể xóa");
		}
		return "redirect:/admin/bill"; 
	}
}
