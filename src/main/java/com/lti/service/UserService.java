package com.lti.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lti.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	List<UserDto> getAllUsers();
	UserDto getUserById(int id);
	UserDto updateUser(UserDto user,int id);
	void deleteUser(int id);

}
