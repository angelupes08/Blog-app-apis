package com.lti.controller;

import java.util.List;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lti.payloads.PostDto;
import com.lti.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService pService;
	
	@PostMapping("/user/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer categoryId
			){
				
		PostDto pDto = pService.createPost(postDto, categoryId);
		return new ResponseEntity<PostDto>(pDto,HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUser(Integer userId){
		
		return new ResponseEntity<List<PostDto>>(pService.getPostsByUser(userId),HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<List<PostDto>> getPostsOfUser(){

		return new ResponseEntity<List<PostDto>>(pService.getPostsByUser(),HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId){
		
		return new ResponseEntity<List<PostDto>>(pService.getPostsByCategory(categoryId),HttpStatus.OK);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable int postId){

		return new ResponseEntity<>(pService.getPostsById(postId),HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<PostDto>> findAllPosts(
			@RequestParam(value = "pageNo",required = false,defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize){

		return new ResponseEntity<>(pService.findAllPosts(pageNo,pageSize),HttpStatus.OK);
	}

}
