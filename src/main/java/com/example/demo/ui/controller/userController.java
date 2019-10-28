package com.example.demo.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //when we declare this annotation it will able to consume rest api when it url matches
@RequestMapping("users")  // this annotation match the URL for rest API
public class userController {

	// get user by request params example http://localhost:8080/users?page=1&limit=50
	// getUserByQuery(@RequestParam(value = "page"\) int page,@RequestParam(value = "limit") int limit){
	
	// by default all params are required
	// if we want to set any default value in params fo the api if not sended from UI
	// getUserByQuery(@RequestParam(value = "page",defaultValue = value) int page,@RequestParam(value = "limit",defaultValue = value) int limit){
	
	// we can customize our parameter using 
	//request params example http://localhost:8080/users?page=1&limit=50
//	getUserByQuery(@RequestParam(value = "page") int page,@RequestParam(value = "limit") int limit 
//			,@RequestParam(value = "sort",defaultValue = desc,required = false) String sort
	// this allow sort to be optional i.e it may or maynot be send in params if not send it will take default value as desc
			
	@GetMapping()
	public String getUserByQuery(@RequestParam(value = "page") int page,@RequestParam(value = "limit") int limit 
			,@RequestParam(value = "sort",defaultValue = "desc",required = false) String sort){
		return "get user get called userid with page= "+page+" , limit = "+limit+" and sortby = "+sort;
	}
	
	// get API with @PathVariable example http://localhost:8080/users/55455
	@GetMapping(path = "/{user_id}")
	public String getUser(@PathVariable String user_id){
		return "get user get called userid = "+user_id;
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
