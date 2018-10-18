package com.pops.restWebService.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

	@Autowired
	private UserDAOService service;
	
	@GetMapping(path = "/user")
	public List<User> getAllUsers() {
		return service.findAll();
	}

	@GetMapping(path = "/user/{userID}")
	public User findUser(@PathVariable int userID) {
		return service.fineOne(userID);
	}
	
//	input - detail of user
//	output - return URI of created user
	@PostMapping(path = "/user")
	public void createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		
	}
}
