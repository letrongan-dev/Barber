package com.myproject.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.dto.BlogDto;
import com.myproject.dto.BlogDtoActive;
import com.myproject.service.BlogService;
import com.myproject.util.FileUploadUtil;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogServ;
	
	@GetMapping(value = "/admin/blog")
	public String listBlog(ModelMap model) {
		List<BlogDto> dtos = blogServ.findAll();
		model.addAttribute("blogs", dtos);
		return "blog/list";
	}
	@GetMapping(value = "/admin/blog/add")
	public String addBlog(ModelMap model) {
		BlogDto dto = new BlogDto();
		model.addAttribute("blog", dto);
		return "blog/add";
	}
	@PostMapping(value = "/admin/blog/save")
	public String saveBlog(@Valid @ModelAttribute("blog")BlogDto blogDto,BindingResult bindingResult, 
			@RequestParam("image")MultipartFile multipartFile,
			ModelMap model, RedirectAttributes re ) throws IOException {
		if(bindingResult.hasErrors() || multipartFile.getSize()==0) {
			return "blog/add";
		}else{
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			blogDto.setImgBlog(fileName);
			blogServ.add(blogDto);
	        String uploadDir = "upload/blog";
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	        re.addFlashAttribute("success", "Thêm thành công!");
	        return "redirect:/admin/blog";
		}
		
	}
	@GetMapping(value = "/admin/blog/active")
	public String activeBlog(@RequestParam("id")int id, RedirectAttributes re) {
		BlogDto dto = blogServ.findById(id);
		int result = 0;
		switch (dto.getStatus()) {
		case 0:
			dto.setStatus(1);
			result = blogServ.activeBlog(dto);
			re.addFlashAttribute("success", "Kích hoạt thành công!");
			break;
		case 1:
			dto.setStatus(0);
			result = blogServ.activeBlog(dto);
			re.addFlashAttribute("success", "Kích hoạt thành công!");
			break;
		default:
			re.addFlashAttribute("error", "Không thể hiển thị!");
			break;
		}
		return "redirect:/admin/blog";		
	}
	@GetMapping(value = "admin/blog/edit")
	public String edit(@RequestParam("id")int id, ModelMap model) {
		BlogDto dto = blogServ.findById(id);
		model.addAttribute("blog", dto);
		return "blog/edit";
	}
	@PostMapping(value = "admin/blog/update")
	public String update(@Valid @ModelAttribute("blog") BlogDto blogDto, BindingResult bindingResult, RedirectAttributes re,
			@RequestParam("image")MultipartFile multipartFile) throws IOException {
		if(bindingResult.hasErrors()) {
			return "blog/edit";
		}else if(multipartFile.getSize()!= 0) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			blogDto.setImgBlog(fileName);
			blogServ.edit(blogDto);
	        String uploadDir = "upload/blog";
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}else {
			blogDto.setImgBlog(blogServ.findById(blogDto.getId()).getImgBlog());
			blogServ.edit(blogDto);
		}
		re.addFlashAttribute("success", "Thêm thành công!");
		return "redirect:/admin/blog";
	}
	
	@GetMapping(value = "/blog")
	public String index(ModelMap model) {
		List<BlogDto> dtos = blogServ.findAll();
		model.addAttribute("blogs", dtos);
		return "blog/index";
	}
	@GetMapping(value = "/blog/detail")
	public String getDetail(@RequestParam("id") int id, ModelMap model) {
		BlogDto dto = blogServ.findById(id);
		model.addAttribute("blogDetail", dto);
		return "blog/detail";
	}
	
}
