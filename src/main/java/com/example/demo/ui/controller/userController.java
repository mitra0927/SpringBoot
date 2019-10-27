package com.example.demo.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //when we declare this annotation it will able to consume rest api when it url matches
@RequestMapping("users")  // this annotation match the URL for rest API
public class userController {

	@GetMapping
	public String getUser(){
		return "get user get called";
	}
	
	@PostMapping
	public String postUser(){
		return "post user get called";
	}
	
	@PutMapping
	public String updateUser(){
		return "update user get called";
	}
	
	@DeleteMapping
	public String deleteUser(){
		return "delete user get called";
	}
}
