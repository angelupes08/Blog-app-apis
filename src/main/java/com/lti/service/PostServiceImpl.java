package com.lti.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.CategoryRepo;
import com.lti.dao.PostRepo;
import com.lti.dao.UserRepo;
import com.lti.entity.Category;
import com.lti.entity.Post;
import com.lti.entity.User;
import com.lti.exceptions.ResourceNotFoundException;
import com.lti.payloads.PostDto;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo pRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo cRepo;

	@Autowired
	private UserService userService;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

		User user = userRepo.findById(userService.getLoggedInUser().getId()).orElseThrow(()->new ResourceNotFoundException("There exists no such user with id"+userId));

		Category category = cRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("There exists no such category with id"+categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);

		Post newPost = pRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("There exists no such user with id"+userId));
		
		List<Post> posts = pRepo.findByUser(user);
		
		List<PostDto> p = new ArrayList<>();
		
		for(int i=0;i<posts.size();i++) {
			
			p.add(this.modelMapper.map(posts.get(i), PostDto.class));
		}

		
		return p;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category category = cRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("There exists no such category with id"+categoryId));
		
		List<Post> posts = pRepo.findByCategory(category);
		
		List<PostDto> p = new ArrayList<>();
		
		for(int i=0;i<posts.size();i++) {
			
			p.add(this.modelMapper.map(posts.get(i), PostDto.class));
		}

		
		return p;
	}

	@Override
	public PostDto getPostsById(Integer postId) {

		Post post = pRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("No posts found with given id"));


		return this.modelMapper.map(post,PostDto.class);
	}

}
