package com.pops.restWebService.customer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// customer resource or controller
// this is more of a resource
@RestController
public class CustomerJPAResource {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PostRepository postRepository;

//	internationalization for RESTful

	@GetMapping(path = "/customer")
	public List<Customer> getAllcustomers() {
		System.out.println("pop");
		return customerRepository.findAll();
	}

	@GetMapping(path = "/customer/{customerID}")
	public Resource<Customer> findcustomer(@PathVariable int customerID) {

//		when you find by ID it exists or not
//		this returns back with a proper object
		Optional<Customer> customer = customerRepository.findById(customerID);

		if (!customer.isPresent())
			throw new CustomerNotFoundException("id -" + customerID);

//		all customers, SERVER_PATH + /customers

//		HATEOAS
//		resource says this is the data we want to use
		Resource<Customer> resource = new Resource<Customer>(customer.get());

//		enables to create links from methods
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllcustomers());

//		what link do i want to use to get to it? it's called /all-customers
		resource.add(linkTo.withRel("all-customers"));

		return resource;
	}

//	void will give you a 200 error a empty thing
	@DeleteMapping(path = "/customer/{customerID}")
	public void deletecustomer(@PathVariable int customerID) {
		customerRepository.deleteById(customerID);
	}

//	input - detail of customer
//	output - return URI of created customer
//	ServletUriComponentsBuilder IS IMPORTANT
//	201 gets returned which is good for created
//	@VALID is to validate the data parameter passed in
	@PostMapping(path = "/customer")
	public ResponseEntity<Object> createcustomer(@Valid @RequestBody Customer customer) {
		Customer savedcustomer = customerRepository.save(customer);

//		System.out.println("Pops:" + ServletUriComponentsBuilder.fromCurrentRequest().toString());
//		Builds localhost:8080/customer and appends path customerID and replaces that with current getID
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerID}")
				.buildAndExpand(savedcustomer.getId()).toUri();

//		header returns this new localhost:8080/customer/4
		return ResponseEntity.created(location).build();

	}

	@GetMapping(path = "/customer/{customerID}/posts")
	public List<Post> retrieveAllPostsByUser(@PathVariable int customerID){
		Optional<Customer> customer = customerRepository.findById(customerID);
		
		if(!customer.isPresent())
			throw new CustomerNotFoundException("id #" + customerID);
		
		
		return customer.get().getPosts();
		
	}
	
	@PostMapping(path = "/customer/{customerID}/posts")
	public ResponseEntity<Object> postByUser(@PathVariable int customerID,@RequestBody Post post){
		Optional<Customer> customerOptional = customerRepository.findById(customerID);
		
		if(!customerOptional.isPresent())
			throw new CustomerNotFoundException("id #" + customerID);
		
		Customer customer = customerOptional.get();
		
		post.setCustomer(customer);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerID}")
				.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}


}
