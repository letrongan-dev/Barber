package com.myproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproject.dto.BlogDto;

public interface BlogService {
	List<BlogDto> findAll();
	void add (BlogDto blogDto);
	BlogDto findById(int id);
	int delete(int id);
	void edit(BlogDto dto);
	int activeBlog (BlogDto dto);
}
