package com.api.mocktailstore.controllers;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.mocktailstore.entities.Blog;
import com.api.mocktailstore.service.BlogService;

@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	private BlogService blogService;

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public ResponseEntity<String> saveBlog(@Valid @RequestBody Blog blog) {
		LOGGER.info("Received a request to save blog: " + blog.toString());
		final Blog savedBlog = blogService.saveBlog(blog);
		ResponseEntity<String> response = null;
		if (!Objects.isNull(savedBlog)) {
			response = new ResponseEntity<>(blogService.getJsonString(savedBlog).toString(), HttpStatus.OK);
		} else {
			JSONObject exceptionJson = new JSONObject();
			exceptionJson.put("message", "internal error");
			response = new ResponseEntity<>(exceptionJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAllBlogs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getAllBlogs() {
		LOGGER.info("Received a request to get all blogs");
		final List<Blog> blogs = blogService.getAllBlogs();
		final JSONArray jsonBlogs = new JSONArray(blogs);
		ResponseEntity<String> response = null;
		try {
			if (blogs.isEmpty()) {
				LOGGER.info("Blogs do not exist");
				response = new ResponseEntity<>(jsonBlogs.toString(), HttpStatus.NO_CONTENT);
			} else {
				LOGGER.info("All Blogs -> " + jsonBlogs.toString());
				response = new ResponseEntity<>(jsonBlogs.toString(), HttpStatus.OK);
			}
		} catch (JSONException e) {
			LOGGER.error("Error while converting to JSON");
			JSONObject exceptionJson = new JSONObject();
			exceptionJson.put("message", "internal error");
			response = new ResponseEntity<>(exceptionJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getBlog", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getBlogById(@RequestParam long blogId) {
		LOGGER.info("Received a request to fetch blog with id:" + blogId);
		ResponseEntity<String> response = null;
		try {
			Blog savedBlog = blogService.getBlogById(blogId);
			JSONObject message = new JSONObject();
			if (Objects.isNull(savedBlog)) {
				message.put("message", "Blog does not exist");
				response = new ResponseEntity<>(message.toString(), HttpStatus.NO_CONTENT);
			} else {
				message.put("title", savedBlog.getTitle()).put("content", savedBlog.getContent()).put("author",
						savedBlog.getAuthor());
				response = new ResponseEntity<>(message.toString(), HttpStatus.OK);
			}
		} catch (JSONException e) {
			LOGGER.error("Error while converting to JSON");
			JSONObject exceptionJson = new JSONObject();
			exceptionJson.put("message", "internal error");
			response = new ResponseEntity<>(exceptionJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/editBlog")
	public ResponseEntity<String> editBlog(@RequestBody Blog blog) {
		LOGGER.info("Received a request to edit blog");
		ResponseEntity<String> response = null;
		JSONObject exceptionMessage = new JSONObject();
		exceptionMessage.put("message", "internal error");
		try {
			final Blog savedBlog = blogService.updateBlog(blog);
			String updatedBlogJsonString = blogService.getJsonString(savedBlog);
			if (!Objects.isNull(updatedBlogJsonString)) {
				response = new ResponseEntity<>(updatedBlogJsonString, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(exceptionMessage.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (JSONException e) {
			LOGGER.error("Error occurred while creating json object");
			response = new ResponseEntity<>(exceptionMessage.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
