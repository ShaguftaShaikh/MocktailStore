package com.api.mocktailstore.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.entities.Blog;
import com.api.mocktailstore.repository.BlogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("blogService")
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(BlogService.class);

	private static final boolean DEFAULT_BLOG_VISIBILITY = true;

	public Blog saveBlog(Blog blog) {
		LOGGER.debug("Saving blog -> " + blog.toString());
		blog.setVisible(DEFAULT_BLOG_VISIBILITY);
		return blogRepository.save(blog);
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

	public Blog updateBlog(final Blog blog) {
		LOGGER.debug("Updating blog with id: " + blog.getBlogId());
		Blog savedBlog = blogRepository.findByBlogId(blog.getBlogId());
		savedBlog.setBlogId(blog.getBlogId());
		savedBlog.setAuthor(blog.getAuthor());
		savedBlog.setTitle(blog.getTitle());
		savedBlog.setContent(blog.getContent());
		savedBlog.setVisible(blog.getVisible());
		blogRepository.save(savedBlog);
		LOGGER.debug("updated to: " + savedBlog.toString());
		return savedBlog;
	}

	public String getJsonString(final Blog blog) {
		LOGGER.debug("Converting " + blog.toString() + " to JSON String");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(blog);
			LOGGER.debug("Converted to JSON String: " + jsonString.toString());
		} catch (JsonProcessingException e) {
			LOGGER.error("Could not process to JSON String " + e.getMessage());
		}
		return jsonString.toString();
	}

}
