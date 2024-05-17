package com.lti.service;


import java.util.List;

import com.lti.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lti.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	List<UserDto> getAllUsers();
	UserDto getUserById(int id);
	UserDto updateUser(UserDto user);
	void deleteUser();
	User getLoggedInUser();

}
