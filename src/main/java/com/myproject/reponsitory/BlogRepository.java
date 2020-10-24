package com.myproject.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myproject.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
	@Query("SELECT b FROM Blog b WHERE b.slug = :slug")
	Blog findBySlugDto(@Param("slug") String slug);
	
	@Query("SELECT b FROM Blog b WHERE b.status = :status ORDER BY b.id DESC")
	List<Blog> listActive(@Param("status")int status);
}
