package com.myproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.dto.BlogDto;
import com.myproject.service.BlogService;

@RestController
public class BlogCotrollerApi {

	@Autowired
	private BlogService blogSer;
	
	@GetMapping(value = "/api/blog")
	public ResponseEntity<List<BlogDto>> get(){
		List<BlogDto> blogs = blogSer.findAll();
		if(blogs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<>(blogs,HttpStatus.OK);
	}
	@GetMapping(value = "/api/blog/detail/{id}")
	public ResponseEntity<BlogDto> Object(@PathVariable("id") Integer id) {
		BlogDto blog = blogSer.findById(id);
		if(blog == null) {
			return new ResponseEntity<BlogDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BlogDto>(blog, HttpStatus.OK);
	}
	

}
