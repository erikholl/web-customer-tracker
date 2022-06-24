package io.eho.springdemo.dao;

import java.util.List;

import io.eho.springdemo.entity.Customer;

public interface CustomerDAO {
	
	List<Customer> getCustomers();

	void saveCustomer(Customer customer);

}
