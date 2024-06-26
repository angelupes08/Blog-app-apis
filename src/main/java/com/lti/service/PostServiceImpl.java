package com.lti.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public PostDto createPost(PostDto postDto,Integer categoryId) {

		User user = userRepo.findById(userService.getLoggedInUser().getId()).orElseThrow(()->new ResourceNotFoundException("There exists no such user with id"));

		Category category = cRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("There exists no such category with id"+categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);

		post.setImageName("xyz");

		post.setAddedDate(new Date());
		post.setUpdatedDate(new Date());

		post.setUser(user);
		post.setCategory(category);

		Post newPost = pRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto,Integer postId){

		//User user = userRepo.findById(userService.getLoggedInUser().getId()).orElseThrow(()->new ResourceNotFoundException("There exists no such user with id"));

		Post existingPost = pRepo.findByIdAndUser(postId,userService.getLoggedInUser());

		if(existingPost==null){
			throw new ResourceNotFoundException("There exists no such post for the user");
		}

		existingPost.setTitle(postDto.getTitle()!=null?postDto.getTitle(): existingPost.getTitle());

		existingPost.setContent(postDto.getContent()!=null?postDto.getContent(): existingPost.getContent());

		existingPost.setImageName(postDto.getImageName()!=null?postDto.getImageName():existingPost.getImageName());

		existingPost.setUpdatedDate(new Date());

		pRepo.save(existingPost);

		return modelMapper.map(existingPost,PostDto.class);

	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("There exists no such user with id"+userId));
		
		List<Post> posts = pRepo.findByUser(user);
		
		List<PostDto> p = new ArrayList<PostDto>();
		
		for(int i=0;i<posts.size();i++) {

			p.add(this.modelMapper.map(posts.get(i), PostDto.class));
		}

		
		return p;
	}

	@Override
	public List<PostDto> getPostsByUser() {

//		User user = userRepo.findById(userService.getLoggedInUser().getId()).orElseThrow(()->new ResourceNotFoundException("There exists no such user with id"+userService.getLoggedInUser().getEmail()));

		List<Post> posts = pRepo.findByUser(userService.getLoggedInUser());

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

	@Override
	public List<PostDto> searchPostContaining(String keyword) {

		List<Post> posts = pRepo.findByTitleContaining("%"+keyword+"%");

		return posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).toList();
	}

	@Override
	public List<PostDto> findAllPosts(Integer pageNo,Integer pageSize) {

		Page<Post> page = pRepo.findAll(PageRequest.of(pageNo,pageSize,Sort.Direction.DESC,"id"));

		return page.stream().map((element) -> modelMapper.map(element, PostDto.class)).toList();
	}

	@Override
	public List<PostDto> findPostsBetweenDates(Date startDate, Date endDate) {

		if(startDate==null){
			startDate = new Date(0);
		}
		if(endDate==null){
			endDate = new Date(System.currentTimeMillis());
		}

		List<Post> posts = pRepo.findByAddedDateBetween(startDate, endDate);

		return posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).toList();
	}

}
