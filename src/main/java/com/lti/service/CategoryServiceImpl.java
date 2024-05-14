package com.lti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.CategoryRepo;
import com.lti.entity.Category;
import com.lti.exceptions.ResourceNotFoundException;
import com.lti.payloads.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo cRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public void createCategory(CategoryDto cDto) {
		
		Category category = convertoEntity(cDto);
		
		cRepo.save(category);
		
	}

	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> categories = cRepo.findAll();
		
		List<CategoryDto> ans = new ArrayList<>();
		
		for(int i=0;i<categories.size();i++) {
			ans.add(convertToDto(categories.get(i)));
		}
		return ans;
	}

	@Override
	public CategoryDto getCategoryById(int id) {
		
		Optional<Category> opt = cRepo.findById(id);
		
		Category c = new Category();
		
		if(opt.isPresent()) {
			c = opt.get();
		}
		else {
			throw new ResourceNotFoundException("There exists no such user with id: "+id);
		}
		
		return convertToDto(c);
	}

	@Override
	public void deleteCategory(int id) {
		
		Optional<Category> opt = cRepo.findById(id);
		
		Category c = new Category();
		
		if(opt.isPresent()) {
			c = opt.get();
		}
		else {
			throw new ResourceNotFoundException("There exists no such user with id: "+id);
		}
		
		cRepo.delete(c);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto cDto, int id) {
		
		Optional<Category> opt = cRepo.findById(id);
		
		Category c = new Category();
		
		if(opt.isPresent()) {
			c = opt.get();
		}
		else {
			throw new ResourceNotFoundException("There exists no such user with id: "+id);
		}
		
		c.setName(cDto.getName());
		c.setDescription(cDto.getDescription());
		
		Category savedCategory = cRepo.save(c);
		
		return convertToDto(savedCategory);
	}
	
	public CategoryDto convertToDto(Category category) {
		
		CategoryDto cDto = this.modelMapper.map(category, CategoryDto.class);
		
		return cDto;
	}
	
	public Category convertoEntity(CategoryDto cDto) {
		
		Category c = this.modelMapper.map(cDto, Category.class);
		
		return c;
	}

}
