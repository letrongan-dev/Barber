package com.myproject.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
