package com.pops.restWebService.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// user resource or controller
// this is more of a resource
@RestController
public class UserResource {

	@Autowired
	private UserDAOService service;

//	internationalization for RESTful
	
	
	@GetMapping(path = "/user")
	public List<User> getAllUsers() {

		return service.findAll();
	}

	@GetMapping(path = "/user/{userID}")
	public Resource<User> findUser(@PathVariable int userID) {
		User user = service.fineOne(userID);

		if (user == null)
			throw new UserNotFoundException("id -" + userID);

//		all users, SERVER_PATH + /users
		
//		HATEOAS
//		resource says this is the data we want to use
		Resource<User> resource = new Resource<User>(user);
		
//		enables to create links from methods
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());

//		what link do i want to use to get to it? it's called /all-users
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}

//	void will give you a 200 error a empty thing
	@DeleteMapping(path = "/user/{userID}")
	public void deleteUser(@PathVariable int userID) {
		User user = service.deleteById(userID);

		if (user == null)
			throw new UserNotFoundException("id -" + userID);

	}

//	input - detail of user
//	output - return URI of created user
//	ServletUriComponentsBuilder IS IMPORTANT
//	201 gets returned which is good for created
//	@VALID is to validate the data parameter passed in
	@PostMapping(path = "/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

//		System.out.println("Pops:" + ServletUriComponentsBuilder.fromCurrentRequest().toString());
//		Builds localhost:8080/user and appends path userID and replaces that with current getID
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userID}")
				.buildAndExpand(savedUser.getId()).toUri();

//		header returns this new localhost:8080/user/4
		return ResponseEntity.created(location).build();

	}
}
