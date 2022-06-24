package io.eho.springdemo.service;

import java.util.List;

import io.eho.springdemo.entity.Customer;

public interface CustomerService {
	
	List<Customer> getCustomers();

	void saveCustomer(Customer customer);

	Customer getCustomer(int id);

}
