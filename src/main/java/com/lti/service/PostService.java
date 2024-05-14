package com.lti.service;

import java.util.List;

import com.lti.payloads.PostDto;

public interface PostService {
	
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	public List<PostDto> getPostsByUser(Integer userId);
	
	public List<PostDto> getPostsByCategory(Integer categoryId);

	public PostDto getPostsById(Integer postId);

}
