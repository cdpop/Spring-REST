package com.pops.restWebService.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CustomerDAOService {
	
	private static List<Customer> customer = new  ArrayList<> ();
	
	private static int customerCount = 3;
	
	static {
		customer.add(new Customer(1,"Adam", new Date()));
		customer.add(new Customer(2,"John", new Date()));
		customer.add(new Customer(3,"Jake", new Date()));
	}
	
	public List<Customer> findAll(){
		return customer;
	}
	
	public Customer save(Customer customer) {
		if(customer.getId() == null) {
			customer.setId(++customerCount );
		}
		this.customer.add(customer);
		return customer;
	}
	
	public Customer fineOne(int id) {
		for(Customer customers :this.customer) {
			if(customers.getId() == id) {
				return customers;
			}
		}
		return null;
	}
	
	public Customer deleteById(int id) {
		Iterator<Customer> iterator = customer.iterator();
		while(iterator.hasNext()) {
			Customer customer = iterator.next();
			if(customer.getId() == id) {
				iterator.remove();
				return customer;
			}
		}
		return null;
	}
	
}
