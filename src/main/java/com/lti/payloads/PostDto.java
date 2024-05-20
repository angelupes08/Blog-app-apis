package com.lti.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

@Data
public class PostDto {
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;

	private Date updatedDate;

	private CategoryDto category;
	
	private UserDto user;

	private Set<CommentDto> comments;
	

}
