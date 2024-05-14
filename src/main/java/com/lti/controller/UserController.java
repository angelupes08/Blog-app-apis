package com.lti.controller;

import java.util.List;

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
	
	@PostMapping("/user")//User will be created
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
		
		UserDto u = uService.createUser(userDto);
		
		return new ResponseEntity<>(u,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateuser/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable int id) {
		
		UserDto u = uService.updateUser(userDto, id);
		 
		return new ResponseEntity<>(u,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		List<UserDto> users = uService.getAllUsers();
		
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int id){
		
		UserDto u = uService.getUserById(id);
		
		return new ResponseEntity<>(u,HttpStatus.OK);
	}
	
	@DeleteMapping("deleteuser/{id}")
	public String deleteUser(@PathVariable int id) {
		
		uService.deleteUser(id);
		
		return "The user with id: "+id+ " has been deleted";
	}

}
