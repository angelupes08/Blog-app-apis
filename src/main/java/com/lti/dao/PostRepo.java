package com.lti.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.entity.Category;
import com.lti.entity.Post;
import com.lti.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepo extends JpaRepository<Post, Integer>{

	public List<Post> findByUser(User user);
	
	public List<Post> findByCategory(Category category);

	public Page<Post> findAll(Pageable page);

	public Post findByIdAndUser(Integer postId,User user);

	@Query("select p from Post p where p.title like :key")
	public List<Post> findByTitleContaining(@Param("key") String keyword);






}
