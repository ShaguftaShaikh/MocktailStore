package com.api.mocktailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mocktailstore.entities.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {

	public Blog findByBlogId(long blogId);

}
