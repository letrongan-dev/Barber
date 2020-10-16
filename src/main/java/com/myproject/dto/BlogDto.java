package com.myproject.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;


public class BlogDto {

	private int id;
	
	@NotNull(message = "Tiêu đề không được để trống")
	private String title;
	
	private String slug;
	private String imgBlog;	
	
	@NotNull(message = "Mô tả không được để trống")
	private String description;
	
	@NotNull(message = "Nội dung không được để trống")
	private String content;
	
	private Date date;
	private Date created_at;
	private Date updatedAt;
	private int status;
	
	public BlogDto() {
		super();
	}

	public BlogDto(int id, String title, String slug, String imgBlog, String description, String content, Date date, Date created_at,
			Date updatedAt, int status) {
		super();
		this.id = id;
		this.title = title;
		this.slug = slug;
		this.imgBlog = imgBlog;
		this.description = description;
		this.content = content;
		this.date = date;
		this.created_at = created_at;
		this.updatedAt = updatedAt;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getImgBlog() {
		return imgBlog;
	}

	public void setImgBlog(String imgBlog) {
		this.imgBlog = imgBlog;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
