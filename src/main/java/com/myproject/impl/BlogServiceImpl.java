package com.myproject.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dto.BlogDto;
import com.myproject.dto.BlogDtoActive;
import com.myproject.entity.Blog;
import com.myproject.reponsitory.BlogRepository;
import com.myproject.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogRepository blogResp;
	
	@Override
	public List<BlogDto> findAll() {
		List<Blog> entitys = blogResp.findAll();
		List<BlogDto> dtos = new ArrayList<BlogDto>();
		for(Blog b : entitys) {
			dtos.add(entityChangeDto(b));
		}
		return dtos;
	}

	@Override
	public void add(BlogDto blogDto) {
		Blog entity = dtoChangeEntity(blogDto);
		blogResp.save(entity);
		
	}

	@Override
	public BlogDto findById(int id) {
		Blog entity = blogResp.getOne(id);
		BlogDto dto = entityChangeDto(entity);
		return dto;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void edit(BlogDto dto) {
		Blog entity = blogResp.getOne(dto.getId());
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setSlug(dto.getSlug());
		entity.setDescription(dto.getDescription());
		entity.setImgBlog(dto.getImgBlog());
		entity.setStatus(dto.getStatus());
		blogResp.save(entity);
		
	}
	
	public BlogDto entityChangeDto(Blog entity) {
		BlogDto dto = new BlogDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setSlug(entity.getSlug());
		dto.setDescription(entity.getDescription());
		dto.setImgBlog(entity.getImgBlog());
		dto.setStatus(entity.getStatus());
		dto.setDate(entity.getDate());
		dto.setImagePhotoPath("/upload/blog/"+entity.getImgBlog());
		return dto;
	}
	public Blog dtoChangeEntity(BlogDto dto) {
		Blog entity = new Blog();
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setSlug(dto.getSlug());
		entity.setDescription(dto.getDescription());
		entity.setImgBlog(dto.getImgBlog());
		entity.setStatus(dto.getStatus());
		entity.setDate(dto.getDate());
		return entity;
	}

	@Override
	public int activeBlog(BlogDto dto) {
		Blog entity = blogResp.getOne(dto.getId());
		entity.setStatus(dto.getStatus());
		entity.setContent(entity.getContent());
		entity.setSlug(entity.getSlug());
		entity.setTitle(entity.getTitle());
		entity.setDescription(entity.getDescription());
		entity.setImgBlog(entity.getImgBlog());
		blogResp.save(entity);
		return 0;
	}

}
