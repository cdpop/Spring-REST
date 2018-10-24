package com.pops.restWebService.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// versioning is easy but choosing right method is hard
@RestController
public class PersonVersioningController {

//	URI versioning
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Pops 1");
	}

	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("pops", "v2"));
	}

//	request parameter versioning
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Pops 1");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("pops", "v2"));
	}

	
//	MISUSE OF HEADER VERSIONING and caching becomes a issue
	
//	header versioning
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Pops 1");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("pops", "v2"));
	}

	
//	accept type versioning
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producersV1() {
		System.out.println("Pop 1");
		return new PersonV1("Pops 1");
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producersV2() {
		System.out.println("Pop 2");
		return new PersonV2(new Name("pops", "v2"));
	}

}
