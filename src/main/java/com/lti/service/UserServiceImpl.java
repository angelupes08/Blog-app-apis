package com.lti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = DtotoUser(userDto);
		User savedUser = userRepo.save(user);
		
		return usertoDto(savedUser);
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
	
	public User DtotoUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	public UserDto usertoDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

}
