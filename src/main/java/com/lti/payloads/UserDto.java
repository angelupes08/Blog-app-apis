package com.lti.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;

	private Date createdDate;;

	private Date updatedDate;


}
