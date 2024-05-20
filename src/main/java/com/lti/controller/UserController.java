package com.lti.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.payloads.UserDto;
import com.lti.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	UserService uService;

	@Operation(summary = "Updates user details")
	@PutMapping("/updateuser")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
		
		UserDto u = uService.updateUser(userDto);
		 
		return new ResponseEntity<>(u,HttpStatus.OK);
	}

	@Operation(summary = "Find all users")
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		List<UserDto> users = uService.getAllUsers();
		
		return new ResponseEntity<>(users,HttpStatus.OK);
	}

	@Operation(summary = "Get users by Id")
	@GetMapping("user")
	public ResponseEntity<UserDto> getUserById(){
		
		UserDto u = uService.getUserDetails();
		
		return new ResponseEntity<>(u,HttpStatus.OK);
	}

	@Operation(summary = "Delete a user")
	@DeleteMapping("deleteuser")
	public String deleteUser() {
		
		uService.deleteUser();
		
		return "The user has been deleted";
	}

}
