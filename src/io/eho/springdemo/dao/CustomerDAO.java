package io.eho.springdemo.dao;

import java.util.List;

import io.eho.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

}
