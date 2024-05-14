package com.lti.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.entity.Category;
import com.lti.entity.Post;
import com.lti.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	public List<Post> findByUser(User user);
	
	public List<Post> findByCategory(Category category);

}
