package com.pops.restWebService.customer;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description="Customer detail such as name/bday")
@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private Integer id;
	
//	min 2 string
	@Size(min=2, message = "Name should have at least 2 characters")
	@ApiModelProperty(notes="Name at least 2 characters")
	private String name;
	
	
//	can be only in past
	@Past
	@ApiModelProperty(notes="Birthday must be in past")
	private Date birthDate;
	
	@OneToMany(mappedBy="customer")
	private List<Post> posts;
	
//	default no argument constructor needed
	protected Customer() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	public Customer(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	
}
