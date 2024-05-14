package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.payloads.CategoryDto;
import com.lti.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService cService;
	
	
	@PostMapping("")
	public ResponseEntity<String> createCategory(@RequestBody CategoryDto cDto){
		
		cService.createCategory(cDto);
		
		return new ResponseEntity<String>("Category created",HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		
		return new ResponseEntity<>(cService.getCategories(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id){
		
		return new ResponseEntity<>(cService.getCategoryById(id),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable int id, @RequestBody CategoryDto cDto){
		
		return new ResponseEntity<CategoryDto>(cService.updateCategory(cDto, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable int id){
		
		cService.deleteCategory(id);
		
		return "The category"+" "+id+"has been deleted";
	}
	
	

}
