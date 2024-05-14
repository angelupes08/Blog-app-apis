package com.lti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
