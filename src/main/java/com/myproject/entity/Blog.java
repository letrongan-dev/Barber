package com.myproject.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "blog")
public class Blog {

	@Column(name = "id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "slug", nullable = false)
	private String slug;
	
	@Column(name = "img_blog", nullable = false)
	private String imgBlog;	
	
	@Column(name = "description", length = 100, nullable = false)
	private String description;
	
	@Lob 
	@Column(name = "content", length = 512, nullable = false)
	private String content;
	
	@Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date date;
	
	@Basic(optional = false)
	@Column(name = "createdAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;
	
	
	@Basic(optional = false)
	@Column(name = "updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(name="status", columnDefinition = "integer default 0", length = 1 )
	private int status;

	public Blog() {}

	public Blog(int id, String title, String slug, String imgBlog, String description, String content, Date date,
			Date created_at, Date updatedAt, int status) {
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
