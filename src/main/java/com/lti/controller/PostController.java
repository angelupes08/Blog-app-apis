package com.lti.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.lti.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lti.payloads.PostDto;
import com.lti.service.PostService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService pService;

	@Autowired
	FileService fileService;

	@Value("${project.image}")
	private String path;

	@Operation(summary = "Create a Post")
	@PostMapping("/user/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer categoryId
			){
				
		PostDto pDto = pService.createPost(postDto, categoryId);
		return new ResponseEntity<PostDto>(pDto,HttpStatus.CREATED);
		
		
	}

	@Operation(summary = "Update a post")
	@PutMapping("/user/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId,@RequestBody PostDto postDto){

		return new ResponseEntity<PostDto>(pService.updatePost(postDto,postId),HttpStatus.OK);
	}

	@Operation(summary = "See any user's posts")
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUser(Integer userId){
		
		return new ResponseEntity<List<PostDto>>(pService.getPostsByUser(userId),HttpStatus.OK);
	}

	@Operation(summary = "See user's posts")
	@GetMapping("/user")
	public ResponseEntity<List<PostDto>> getPostsOfUser(){

		return new ResponseEntity<List<PostDto>>(pService.getPostsByUser(),HttpStatus.OK);
	}

	@Operation(summary = "See posts of users by categories")
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId){
		
		return new ResponseEntity<List<PostDto>>(pService.getPostsByCategory(categoryId),HttpStatus.OK);
	}

	@Operation(summary = "See posts by Id")
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable int postId){

		return new ResponseEntity<>(pService.getPostsById(postId),HttpStatus.OK);
	}

	@Operation(summary = "Find all posts")
	@GetMapping("")
	public ResponseEntity<List<PostDto>> findAllPosts(
			@RequestParam(value = "pageNo",required = false,defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize){

		return new ResponseEntity<>(pService.findAllPosts(pageNo,pageSize),HttpStatus.OK);
	}

	//post image upload
	@Operation(summary = "Upload images to posts")
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image")MultipartFile image,
			@PathVariable Integer postId
			) throws IOException {
		String fileName = fileService.uploadImage(path,image);

		PostDto postDto = pService.getPostsById(postId);
		postDto.setImageName(fileName);

		PostDto updatePost = pService.updatePost(postDto,postId);

		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}

	@Operation(summary = "Search posts by title")
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keyword){

		return new ResponseEntity<List<PostDto>>(pService.searchPostContaining(keyword),HttpStatus.OK);
	}

	@Operation(summary = "Search posts between dates")
	@GetMapping("/searchdate")
	public ResponseEntity<List<PostDto>> readPostsByDate(
			@RequestParam(value = "startDate",required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
			@RequestParam(value = "endDate",required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate){

		return new ResponseEntity<List<PostDto>>(pService.findPostsBetweenDates(startDate,endDate),HttpStatus.OK);
	}

}
