package com.pops.restWebService.versioning;

// original versionjust name  
public class PersonV1 {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonV1(String name) {
		super();
		this.name = name;
	}

	public PersonV1() {
	}

}
