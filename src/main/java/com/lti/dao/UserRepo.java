package com.lti.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.lti.entity.User;
import com.lti.payloads.UserDto;

public interface UserRepo extends JpaRepository<User, Integer>{

    public Optional<User> findByEmail(String email);
}
