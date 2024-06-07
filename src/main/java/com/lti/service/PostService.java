package com.lti.service;

import java.util.Date;
import java.util.List;

import com.lti.payloads.PostDto;
import org.springframework.data.domain.Pageable;

public interface PostService {
	
	public PostDto createPost(PostDto postDto,Integer categoryId);

	public PostDto updatePost(PostDto postDto,Integer postId);
	
	public List<PostDto> getPostsByUser(Integer userId);

	public List<PostDto> getPostsByUser();
	
	public List<PostDto> getPostsByCategory(Integer categoryId);

	public PostDto getPostsById(Integer postId);

	public List<PostDto> searchPostContaining(String keyword);

	public List<PostDto> findAllPosts(Integer pageNo,Integer pageSize);

	public List<PostDto> findPostsBetweenDates(Date startDate, Date endDate);


}
