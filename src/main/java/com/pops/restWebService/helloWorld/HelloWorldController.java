package com.pops.restWebService.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

	@GetMapping(path="/hello")
	public String helloWorld() {
		return "Hello world";
	}
	
	@GetMapping(path="/hello/bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("HelloWorld");
	}
	
	@GetMapping(path="/hello/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World %s", name));
	}
	
}
