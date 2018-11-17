package com.api.mocktailstore.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.entities.Blog;
import com.api.mocktailstore.repository.BlogRepository;

@Service("blogService")
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(BlogService.class);

	public void saveBlog(Blog blog) {
		/**
		 * TODO: manipulation of blog content string, if required.
		 */
		blogRepository.save(blog);
	}

	/**
	 * gets all the blogs
	 * 
	 * @return a list of blogs
	 */
	public List<Blog> getAllBlogs() {
		List<Blog> allBlogs = blogRepository.findAll();
		LOGGER.debug("fetching all blogs, count:" + allBlogs.size() + ", blogs -> " + allBlogs.toString());
		return allBlogs;
	}

	/**
	 * gets the blog with id:long
	 * 
	 * @param blogId
	 *            contains id of the blog
	 * @return Blog object
	 */
	public Blog getBlogById(final long blogId) {
		Blog savedBlog = blogRepository.findByBlogId(blogId);
		LOGGER.debug("fetching blog with id:" + blogId + ", blog -> " + savedBlog.toString());
		return savedBlog;
	}

}
