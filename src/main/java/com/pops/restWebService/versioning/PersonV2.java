package com.pops.restWebService.versioning;

// new version we want first/last split but old person want name as string
public class PersonV2 {

	private Name name;

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	public PersonV2() {
	}

}
