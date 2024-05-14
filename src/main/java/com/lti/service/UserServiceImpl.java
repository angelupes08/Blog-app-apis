package com.lti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lti.exceptions.ItemAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lti.dao.UserRepo;
import com.lti.entity.User;
import com.lti.exceptions.ResourceNotFoundException;
import com.lti.payloads.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {

		Optional<User> opt = userRepo.findByEmail(userDto.getEmail());

		if(opt.isPresent()){
			throw new ItemAlreadyExistsException("An account already exists with given email"+userDto.getEmail());
		}

		User newUser = this.modelMapper.map(userDto,User.class);
		newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		return usertoDto(userRepo.save(newUser));
	}

	@Override
	public  List<UserDto> getAllUsers() {
		
		List<UserDto> userlist = new ArrayList<>();
		
		List<User> users = userRepo.findAll();
		
		for(User u:users) {
			UserDto uDto = usertoDto(u);
			userlist.add(uDto);
		}
		
		return userlist;
	}

	@Override
	public UserDto getUserById(int id) {
		
		Optional<User> opt = userRepo.findById(id);
		
		User user = new User();
		
		if(opt.isPresent()) {
			user = opt.get();
		}
		else {
			throw new ResourceNotFoundException("There exists no such user with id: "+id);
		}
		
		return usertoDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id) {
		
		Optional<User> opt = userRepo.findById(id);
		
		User user = new User();
		
		if(opt.isPresent()) {
			user = opt.get();
		}
		else {
			throw new ResourceNotFoundException("There exists no such user with id: "+id);
		}
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User savedUser = userRepo.save(user);
		
		return usertoDto(savedUser);
	}

	@Override
	public void deleteUser(int id) {
		
		Optional<User> opt = userRepo.findById(id);
		
		User user = new User();
		
		if(opt.isPresent()) {
			user = opt.get();
		}
		else {
			throw new ResourceNotFoundException("There exists no such user with id: "+id);
		}
		
		userRepo.delete(user);
	}

	@Override
	public User getLoggedInUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email"+email));
	}

	public User DtotoUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);

		return user;
	}
	
	public UserDto usertoDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		return userDto;
	}

}
