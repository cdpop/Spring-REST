package com.pops.restWebService.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	
	private static List<User> user = new  ArrayList<> ();
	
	private static int userCount = 3;
	
	static {
		user.add(new User(1,"Adam", new Date()));
		user.add(new User(2,"John", new Date()));
		user.add(new User(3,"Jake", new Date()));
	}
	
	public List<User> findAll(){
		return user;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount );
		}
		this.user.add(user);
		return user;
	}
	
	public User fineOne(int id) {
		for(User users :this.user) {
			if(users.getId() == id) {
				return users;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = user.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
}
