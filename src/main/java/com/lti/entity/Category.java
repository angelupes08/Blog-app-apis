package com.lti.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private String description;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch=FetchType.LAZY) 
	private List<Post> posts;

}
