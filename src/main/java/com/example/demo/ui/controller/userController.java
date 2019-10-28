package com.example.demo.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ui.model.request.UserDetailRequestModel;
import com.example.demo.ui.model.response.UserRest;

@RestController // when we declare this annotation it will able to consume rest
				// api when it url matches
@RequestMapping("users") // this annotation match the URL for rest API
public class userController {
	
	Map<String , UserRest> users;

	// step 1
	// get user by request params example
	// http://localhost:8080/users?page=1&limit=50
	// getUserByQuery(@RequestParam(value = "page"\) int
	// page,@RequestParam(value = "limit") int limit){

	// by default all params are required
	// if we want to set any default value in params fo the api if not sended
	// from UI
	// getUserByQuery(@RequestParam(value = "page",defaultValue = value) int
	// page,@RequestParam(value = "limit",defaultValue = value) int limit){

	// we can customize our parameter using
	// request params example http://localhost:8080/users?page=1&limit=50
	// getUserByQuery(@RequestParam(value = "page") int page,@RequestParam(value
	// = "limit") int limit
	// ,@RequestParam(value = "sort",defaultValue = desc,required = false)
	// String sort
	// this allow sort to be optional i.e it may or maynot be send in params if
	// not send it will take default value as desc

	@GetMapping()
	public String getUserByQuery(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "get user get called userid with page= " + page + " , limit = " + limit + " and sortby = " + sort;
	}

	// get API with @PathVariable example http://localhost:8080/users/55455
	/*
	 * @GetMapping(path = "/{user_id}") public String getUser(@PathVariable
	 * String user_id){ return "get user get called userid = "+user_id; }
	 */

	// return json response to the API
	/*
	 * produces =
	 * {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE} WILL
	 * AUTOMATICALLY SUPPORT BOTH JSON AND XML RESPONSE FROM API BUT WE NEED TO
	 * DO SMALL CHANGE IN PORM.XML TO ADD A DEPENDENCY WHICH SUPPORT XML use
	 * https://mvnrepository.com/search?q=jackson+xml and type jackson xml
	 * select Jackson Dataformat XML
	 */
	/*
	 * we had created UserRest class to send response back to ui
	 * 
	 * @GetMapping(path = "/{user_id}", produces = {
	 * MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 * public UserRest getUser(@PathVariable String user_id) {
	 * 
	 * UserRest returnValue = new UserRest();
	 * returnValue.setEmail("firdous.alam2058@gmail.com");
	 * returnValue.setFirstName("Firdous"); ; returnValue.setLastName("Alam");
	 * returnValue.setUserId("1");
	 * 
	 * // this will return json to UI return returnValue; }
	 * 
	 */
	// step 3
	// how to send custom http status code

	/*
	@GetMapping(path = "/{user_id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String user_id) {

		UserRest returnValue = new UserRest();
		returnValue.setEmail("firdous.alam2058@gmail.com");
		returnValue.setFirstName("Firdous");
		;
		returnValue.setLastName("Alam");
		returnValue.setUserId("1");

		// this will return json to UI
		return new ResponseEntity<UserRest>(HttpStatus.BAD_REQUEST);
	}
	*/
	// in step 4 we will run post method
	// in order to read JSON of UI we have to create one class that will hold
	// the details of JSON which are send from UI.
	/*
	 * note we had created one new Class UserDetailRequestModel.java and the
	 * variable which we declare there must match with request JSON this will
	 * help us to create javaObject from Json
	 * 
	 * we will use @RequestBody to read request data
	 * 
	 * so it will read data from request and passit to UserDetailRequestModel
	 * and we can access those data using userDetails
	 * 
	 * consumes = { MediaType.APPLICATION_XML_VALUE,
	 * MediaType.APPLICATION_JSON_VALUE } mean our API can consume both JSON and
	 * XML from UI so the below API can consume both JSON and XML and also send
	 * both JSON and XML
	 */

	/*
	 * @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE,
	 * MediaType.APPLICATION_JSON_VALUE }, produces = {
	 * MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 * public ResponseEntity<UserRest> postUser(@RequestBody
	 * UserDetailRequestModel userDetails) { UserRest returnValue = new
	 * UserRest(); returnValue.setEmail(userDetails.getEmail());
	 * returnValue.setFirstName(userDetails.getFirstName()); ;
	 * returnValue.setLastName(userDetails.getLastName());
	 * 
	 * // this will return json to UI return new
	 * ResponseEntity<UserRest>(returnValue,HttpStatus.OK); }
	 */

	/*
	 * step5
	 * 
	 * in this section we will learn how to validate data spring boot by default
	 * support validation but we have to add @Valid annotation to initiate
	 * validation or we can use any hibernate spring validation
	 * 
	 * 
	 * 
	 * 
	 */

	@GetMapping(path = "/{user_id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String user_id) {
		if(users.containsKey(user_id))
		{
			return new ResponseEntity<>(users.get(user_id),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> postUser(@Valid @RequestBody UserDetailRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		// step 6 from here
		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		if(users == null) users = new HashMap<String, UserRest>();
		users.put(userId, returnValue);
		// this will return json to UI
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}

	@PutMapping
	public String updateUser() {
		return "update user get called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "delete user get called";
	}
}
